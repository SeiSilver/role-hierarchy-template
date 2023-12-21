package com.spring.template.silver.app.usecase.exception;

import com.spring.template.silver.app.usecase.exception.error.ErrorMessage;

public class DataNotFoundException extends ApplicationException {

  public DataNotFoundException() {
    super(ErrorMessage.DATA_NOT_FOUND);
  }
  public DataNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
