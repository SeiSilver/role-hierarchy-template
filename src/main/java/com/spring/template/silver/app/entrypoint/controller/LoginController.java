package com.spring.template.silver.app.entrypoint.controller;

import com.spring.template.silver.app.entrypoint.handler.LoginHandler;
import com.spring.template.silver.app.entrypoint.payload.login.LoginRequest;
import com.spring.template.silver.app.entrypoint.payload.login.LoginResponse;
import com.spring.template.silver.app.usecase.constraint.StringConstraint;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LoginController {

  private final LoginHandler loginHandler;

  @PostMapping("/login")
  public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) throws DataNotFoundException {
    return loginHandler.loginHandler(loginRequest);
  }

  @GetMapping("/random")
  @PreAuthorize("hasRole('ROLE_USER')")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public Map<String, String> randomStuff() {
    Map<String, String> data = new HashMap<>();
    data.put("message", "JWT Hợp lệ mới có thể thấy được message này");
    return data;
  }

}