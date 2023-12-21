package com.spring.template.silver.app.infrastructure.dto;

import java.time.ZonedDateTime;

public interface ProductInfo {

  Integer getId();

  String getName();

  String getDescription();

  Integer getPrice();

  String getCategoryName();

  ZonedDateTime getModifiedAt();

  ZonedDateTime getCreatedAt();

}