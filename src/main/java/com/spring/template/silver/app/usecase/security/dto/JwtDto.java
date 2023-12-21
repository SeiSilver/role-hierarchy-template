package com.spring.template.silver.app.usecase.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {

  private String accessToken;

}