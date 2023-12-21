package com.spring.template.silver.app.entrypoint.exceptionhandler;

import com.spring.template.silver.app.usecase.exception.ApplicationException;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import com.spring.template.silver.app.usecase.exception.error.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

  private static final String FOUND_AN_ERROR = "Found an error: {}";

  @ExceptionHandler(value = {ApplicationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ErrorDetail handleExceptions(ApplicationException ex) {
    log.error(FOUND_AN_ERROR, ex.getMessage(), ex);
    return ex.getErrorDetail();
  }

  @ExceptionHandler(value = {MissingServletRequestParameterException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ErrorDetail handleRequestParameterExceptions(MissingServletRequestParameterException ex) {
    log.error(FOUND_AN_ERROR, ex.getMessage(), ex);
    return ErrorDetail
        .builder()
        .errorMessage(ex.getMessage())
        .build();
  }

  @ExceptionHandler(value = {DataNotFoundException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  private ErrorDetail handleDataNotFound(DataNotFoundException ex) {
    log.error(FOUND_AN_ERROR, ex.getMessage(), ex);
    return ex.getErrorDetail();
  }

}
