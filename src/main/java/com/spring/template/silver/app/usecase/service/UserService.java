package com.spring.template.silver.app.usecase.service;

import com.spring.template.silver.app.entrypoint.payload.login.LoginRequest;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import com.spring.template.silver.app.usecase.exception.UserNotFoundException;
import com.spring.template.silver.app.infrastructure.entity.AccountEntity;

public interface UserService {

  AccountEntity getUserByEmail(String email) throws UserNotFoundException;

  AccountEntity login(LoginRequest loginRequest) throws DataNotFoundException;

}
