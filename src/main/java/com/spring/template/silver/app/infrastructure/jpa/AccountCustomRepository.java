package com.spring.template.silver.app.infrastructure.jpa;

import com.spring.template.silver.app.infrastructure.dto.UserUpdateRequest;

public interface AccountCustomRepository {

  void update(Long accountId, UserUpdateRequest userUpdateRequest);

}
