package com.spring.template.silver.app.usecase.utils;

import com.spring.template.silver.app.usecase.constraint.ValidationRegexpPattern;
import com.spring.template.silver.app.usecase.exception.error.ErrorMessage;
import com.spring.template.silver.app.usecase.exception.ApplicationException;
import com.spring.template.silver.app.usecase.exception.MissingRequireFieldException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateUtils {

  private static final String REQUIRED_FIELD_ERROR_MESSAGE = "Missing required fields: ";
  private final Validator validator;

  public <T> void validateRequiredFields(T requestPayload) throws MissingRequireFieldException {
    Set<ConstraintViolation<T>> violations = validator.validate(requestPayload);
    if (!violations.isEmpty()) {
      log.error("invalid payload with violations " + violations.stream()
          .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
          .toList());
      List<String> listErrorMessage = violations.stream()
          .map(violation -> violation.getPropertyPath().toString())
          .toList();
      String errorMessage = REQUIRED_FIELD_ERROR_MESSAGE + String.join(", ", listErrorMessage);
      throw new MissingRequireFieldException(errorMessage);
    }
  }

  public static void checkFormatEmail(String email) throws ApplicationException {
    if (!isEmail(email)) {
      throw new ApplicationException(ErrorMessage.INCORRECT_EMAIL_FORMAT);
    }
  }

  private static boolean isEmail(String email) {
    return email.matches(ValidationRegexpPattern.EMAIL);
  }

}
