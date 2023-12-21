package com.spring.template.silver.app.usecase.exception.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {

  public static final String INVALID_JSON_PAYLOAD = "Payload parse exception";
  public static final String AUTHORIZED_FAIL = "Authentication Failed, please try again!";
  public static final String TOKEN_INVALID = "Invalid token!";
  public static final String TOKEN_EXPIRED = "Token expired!";
  public static final String ACCESS_DENIED = "Access denied!";
  public static final String USER_NOT_FOUND = "User not found!";
  public static final String USER_LOCKED = "User is locked!";
  public static final String INCORRECT_EMAIL_FORMAT = "Incorrect email format";
  public static final String DATA_NOT_FOUND = "Data not found!";
  public static final String DATA_EXISTED= "Data already existed!";

}
