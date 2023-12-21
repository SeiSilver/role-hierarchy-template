package com.spring.template.silver.app.usecase.constraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StringConstraint {
  public static final String REDIRECT_URL_KEY = "redirect-url";
  public static final String BEARER = "Bearer";
}
