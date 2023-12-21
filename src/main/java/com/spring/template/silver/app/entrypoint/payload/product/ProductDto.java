package com.spring.template.silver.app.entrypoint.payload.product;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private Integer id;

  private String name;

  private String description;

  private Integer price;

  private String category;

  private ZonedDateTime modifiedAt;

  private ZonedDateTime createdAt;

}
