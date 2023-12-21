package com.spring.template.silver.app.entrypoint.controller;

import com.spring.template.silver.app.entrypoint.handler.ProductHandler;
import com.spring.template.silver.app.entrypoint.payload.product.NewProductRequest;
import com.spring.template.silver.app.entrypoint.payload.product.ProductDto;
import com.spring.template.silver.app.entrypoint.payload.product.UpdateProductRequest;
import com.spring.template.silver.app.infrastructure.dto.ProductInfo;
import com.spring.template.silver.app.usecase.constraint.StringConstraint;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

  private final ProductHandler productHandler;

  @GetMapping("/product")
  public List<ProductDto> getAll() {
    return productHandler.getAllHandler();
  }

  @GetMapping("/product/{id}")
  public ProductDto getById(@PathVariable Integer id) throws DataNotFoundException {
    return productHandler.getByIdHandler(id);
  }

  @GetMapping("/v2/product/{id}")
  public ProductInfo getByIdV2(@PathVariable Integer id) throws DataNotFoundException {
    return productHandler.getByIdV2Handler(id);
  }

  @PostMapping("/product")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public void add(NewProductRequest request) {
    productHandler.addHandler(request);
  }

  @PutMapping("/product")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public void update(UpdateProductRequest request) {
    productHandler.updateHandler(request);
  }

  @DeleteMapping("/product")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public void delete(Integer id) throws DataNotFoundException {
    productHandler.deleteByIdHandler(id);

  }

}