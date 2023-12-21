package com.spring.template.silver.app.usecase.service.impl;

import com.spring.template.silver.app.entrypoint.payload.login.LoginRequest;
import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.infrastructure.jpa.AccountRepository;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import com.spring.template.silver.app.usecase.exception.UserNotFoundException;
import com.spring.template.silver.app.usecase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final AccountRepository accountRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public AccountEntity getUserByEmail(String email) throws UserNotFoundException {
    return accountRepository.findByEmail(email).orElseThrow(
        UserNotFoundException::new
    );
  }

  @Override
  public AccountEntity login(LoginRequest loginRequest) throws DataNotFoundException {
    var accountEntity = accountRepository.findByEmail(loginRequest.getEmail())
        .orElseThrow(() -> new DataNotFoundException("Invalid username or password."));
    if (!passwordEncoder.matches(loginRequest.getPassword(), accountEntity.getPasswordHash())) {
      throw new DataNotFoundException("Invalid username or password.");
    }
    return accountEntity;
  }

}
