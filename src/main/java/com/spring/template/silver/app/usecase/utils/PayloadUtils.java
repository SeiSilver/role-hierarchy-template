package com.spring.template.silver.app.usecase.utils;

import com.spring.template.silver.app.usecase.exception.InvalidJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayloadUtils {

  private final ObjectMapper objectMapper;

  public <T> T parseJson(String data, Class<T> clazz) throws InvalidJsonException {
    try {
      return objectMapper.readValue(data, clazz);
    } catch (JsonProcessingException e) {
      throw new InvalidJsonException(e);
    }
  }

  public <T> T parseJson(String data, TypeReference<T> typeReference) throws InvalidJsonException {
    try {
      return objectMapper.readValue(data, typeReference);
    } catch (JsonProcessingException e) {
      log.error("Can not parse JSON data", e);
      throw new InvalidJsonException(e);
    }
  }

  public String parseObjectToString(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      return null;
    }
  }

}
