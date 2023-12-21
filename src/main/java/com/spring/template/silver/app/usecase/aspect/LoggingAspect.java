package com.spring.template.silver.app.usecase.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.template.silver.app.usecase.annotation.LogMethod;
import liquibase.repackaged.org.apache.commons.lang3.time.StopWatch;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Around("@within(logMethod) || @annotation(logMethod)")
  public Object logMethodExecution(ProceedingJoinPoint pjp, LogMethod logMethod) throws Throwable {
    final MethodSignature signature = (MethodSignature) pjp.getSignature();
    final Method method = signature.getMethod();
    String shortMethod = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
    final StopWatch stopWatch = new StopWatch();
    try {
      final String arguments = IntStream.iterate(0, i -> i + 1)
          .limit(Math.min(signature.getParameterNames().length, pjp.getArgs().length))
          .mapToObj(i -> {
            try {
              return signature.getParameterNames()[i] + "=" + objectMapper.writeValueAsString(pjp.getArgs()[i]);
            } catch (JsonProcessingException e) {
              log.error("cannot parse as JSON value = {}", pjp.getArgs()[i], e);
              throw new RuntimeException(e);
            }
          })
          .collect(Collectors.joining(","));
      log.info("Start execution of {} with arguments: {}", shortMethod, arguments);
      stopWatch.start();
      final Object result = pjp.proceed();
      stopWatch.stop();
      log.info("End execution of {} (running {} ms)", shortMethod, stopWatch.getTime());
      return result;
    } catch (Exception ex) {
      stopWatch.stop();
      log.error("Fail execution of {} (running {} ms)", shortMethod, stopWatch.getTime(), ex);
      throw ex;
    }
  }

  @AfterThrowing(value = "@within(logMethod) || @annotation(logMethod)", throwing = "error")
  public void logAfterThrowing(JoinPoint pjp, LogMethod logMethod, Throwable error) throws InterruptedException {
    final MethodSignature signature = (MethodSignature) pjp.getSignature();
    final Method method = signature.getMethod();
    final String arguments = IntStream.iterate(0, i -> i + 1)
        .limit(Math.min(signature.getParameterNames().length, pjp.getArgs().length))
        .mapToObj(i -> signature.getParameterNames()[i] + "=" + pjp.getArgs()[i])
        .collect(Collectors.joining(","));
    log.error("handle exception in {} with cause = {}, arguments = {}",
        method,
        error.getCause() != null ? error.getCause() : "NULL",
        arguments);
    Thread.sleep(2000);
  }

}
