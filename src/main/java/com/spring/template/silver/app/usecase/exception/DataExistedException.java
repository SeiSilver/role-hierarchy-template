package com.spring.template.silver.app.usecase.exception;

import com.spring.template.silver.app.usecase.exception.error.ErrorMessage;

public class DataExistedException extends ApplicationException {

  public DataExistedException() {
    super(ErrorMessage.DATA_EXISTED);
  }

  public DataExistedException(String errorMessage) {
    super(errorMessage);
  }

}
