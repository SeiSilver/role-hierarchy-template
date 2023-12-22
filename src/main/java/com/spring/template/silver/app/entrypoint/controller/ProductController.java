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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

  private final ProductHandler productHandler;

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/product")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public List<ProductDto> getAll() {
    return productHandler.getAllHandler();
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/product/{id}")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public ProductDto getById(@PathVariable Integer id) throws DataNotFoundException {
    return productHandler.getByIdHandler(id);
  }

  @PreAuthorize("hasRole('ROLE_USER')")
  @GetMapping("/v2/product/{id}")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public ProductInfo getByIdV2(@PathVariable Integer id) throws DataNotFoundException {
    return productHandler.getByIdV2Handler(id);
  }

  @PreAuthorize("hasRole('ROLE_STAFF')")
  @PostMapping("/product")
  @Operation(security = @SecurityRequirement(name = StringConstraint.BEARER))
  public void add(NewProductRequest request) {
    productHandler.addHandler(request);
  }

  @PutMapping("/product")
  @PreAuthorize("hasRole('ROLE_MODERATOR')")
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