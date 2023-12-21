package com.spring.template.silver.app.entrypoint.payload.product;

import com.spring.template.silver.app.infrastructure.entity.CategoryEntity;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewProductRequest {

  @NotNull
  private String name;

  @NotNull
  private String description;

  @NotNull
  private Integer price;

  private CategoryEntity category;

}
