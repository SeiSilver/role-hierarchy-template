package com.spring.template.silver.app.usecase.security.service;

import com.spring.template.silver.app.infrastructure.entity.AccountEntity;
import com.spring.template.silver.app.usecase.exception.UserNotFoundException;
import com.spring.template.silver.app.usecase.security.dto.CustomUserDetails;
import com.spring.template.silver.app.usecase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailServiceImpl implements CustomUserDetailService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    AccountEntity accountEntity;
    try {
      accountEntity = userService.getUserByEmail(email);
    } catch (UserNotFoundException e) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new CustomUserDetails(accountEntity);
  }

}
