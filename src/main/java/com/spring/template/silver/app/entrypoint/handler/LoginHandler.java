package com.spring.template.silver.app.entrypoint.handler;

import com.spring.template.silver.app.entrypoint.payload.login.LoginRequest;
import com.spring.template.silver.app.entrypoint.payload.login.LoginResponse;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import com.spring.template.silver.app.usecase.service.UserService;
import com.spring.template.silver.app.usecase.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginHandler {

  private final JwtUtils jwtUtils;
  private final UserService userService;

  public LoginResponse loginHandler(LoginRequest loginRequest) throws DataNotFoundException {
    var accountEntity = userService.login(loginRequest);
    return LoginResponse.builder()
        .accessToken(jwtUtils.createToken(accountEntity).getAccessToken())
        .build();
  }

}
