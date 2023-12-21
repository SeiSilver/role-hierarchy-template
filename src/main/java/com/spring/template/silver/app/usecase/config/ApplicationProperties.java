package com.spring.template.silver.app.usecase.config;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "application")
@Getter
@RequiredArgsConstructor
@ConstructorBinding
@Validated
public class ApplicationProperties {

  @NotNull
  @Valid
  private final JwtConfig jwtConfig;

  public record JwtConfig(@NotBlank String tokenSecret, @NotNull Integer tokenExpiredSeconds) {

  }

}
