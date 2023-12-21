package com.spring.template.silver.app.usecase.security.service;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.infrastructure.jpa.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware<AccountEntity> {

  private AccountRepository userRepository;

  @Override
  public Optional<AccountEntity> getCurrentAuditor() {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      return Optional.empty();
    }
    final String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return userRepository.findByEmail(email);
  }

}
