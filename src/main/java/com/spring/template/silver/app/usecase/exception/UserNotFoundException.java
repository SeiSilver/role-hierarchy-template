package com.spring.template.silver.app.usecase.exception;

import com.spring.template.silver.app.usecase.exception.error.ErrorMessage;

public class UserNotFoundException extends ApplicationException {

  public UserNotFoundException() {
    super(ErrorMessage.USER_NOT_FOUND);
  }

}
