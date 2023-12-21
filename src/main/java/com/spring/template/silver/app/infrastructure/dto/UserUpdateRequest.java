package com.spring.template.silver.app.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserUpdateRequest {

  private String fullName;

}
