package com.spring.template.silver.app;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.usecase.security.service.AuditorAwareImpl;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(
    dateTimeProviderRef = "auditingDateTimeProvider",
    auditorAwareRef = "auditorProvider"
)
@ConfigurationPropertiesScan({"com.spring.template.silver.app.usecase.config"})
@AllArgsConstructor
public class Application {

  private final AuditorAwareImpl auditorAware;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public DateTimeProvider auditingDateTimeProvider() {
    ZonedDateTime utcZoneDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneOffset.UTC);
    return () -> Optional.of(utcZoneDateTime);
  }

  @Bean
  public AuditorAware<AccountEntity> auditorProvider() {
    return auditorAware;
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }

}
