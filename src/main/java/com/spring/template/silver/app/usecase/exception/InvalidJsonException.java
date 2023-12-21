package com.spring.template.silver.app.usecase.exception;

import com.spring.template.silver.app.usecase.exception.error.ErrorMessage;

public class InvalidJsonException extends ApplicationException {

  public InvalidJsonException() {
    super(ErrorMessage.INVALID_JSON_PAYLOAD);
  }

  public InvalidJsonException(Throwable cause) {
    super(ErrorMessage.INVALID_JSON_PAYLOAD, cause);
  }
}
