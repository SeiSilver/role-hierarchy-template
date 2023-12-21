package com.spring.template.silver.app.usecase.exception;

import lombok.Getter;

@Getter
public class MissingRequireFieldException extends ApplicationException {

  public MissingRequireFieldException(String errorMessage) {
    super(errorMessage);
  }

}
