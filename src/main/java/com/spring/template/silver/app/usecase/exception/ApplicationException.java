package com.spring.template.silver.app.usecase.exception;

import com.spring.template.silver.app.usecase.exception.error.ErrorDetail;
import lombok.Getter;

@Getter
public class ApplicationException extends Exception {

  private final transient ErrorDetail errorDetail;

  public ApplicationException(String errorMessage) {
    super(errorMessage);
    this.errorDetail = ErrorDetail.builder()
        .errorMessage(errorMessage)
        .build();
  }

  public ApplicationException(String errorMessage, Throwable rootCause) {
    super(errorMessage, rootCause);
    this.errorDetail = ErrorDetail.builder()
        .errorMessage(errorMessage)
        .build();
  }

}