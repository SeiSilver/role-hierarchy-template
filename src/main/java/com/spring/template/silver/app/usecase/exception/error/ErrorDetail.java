package com.spring.template.silver.app.usecase.exception.error;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ErrorDetail {

  private String errorMessage;

}

