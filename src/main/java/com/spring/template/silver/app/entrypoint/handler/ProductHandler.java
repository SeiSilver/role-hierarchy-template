package com.spring.template.silver.app.entrypoint.handler;

import com.spring.template.silver.app.entrypoint.payload.product.NewProductRequest;
import com.spring.template.silver.app.entrypoint.payload.product.ProductDto;
import com.spring.template.silver.app.entrypoint.payload.product.UpdateProductRequest;
import com.spring.template.silver.app.infrastructure.dto.ProductInfo;
import com.spring.template.silver.app.infrastructure.entity.ProductEntity;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import com.spring.template.silver.app.usecase.service.ProductService;
import com.spring.template.silver.app.usecase.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductHandler {

  private final ProductService productService;
  private final ModelMapperUtils modelMapperUtils;

  public List<ProductDto> getAllHandler() {
    var productEntityList = productService.getAll();
    return modelMapperUtils.mapList(productEntityList, ProductDto.class);
  }

  public ProductDto getByIdHandler(Integer id) throws DataNotFoundException {
    var productEntity = productService.getById(id);
    return modelMapperUtils.map(productEntity, ProductDto.class);
  }

  public ProductInfo getByIdV2Handler(int id) throws DataNotFoundException {
    return productService.getByIdV2(id);
  }

  public void addHandler(NewProductRequest request) {
    ProductEntity productEntity = ProductEntity.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .category(request.getCategory())
        .build();
    productService.save(productEntity);
  }

  public void updateHandler(UpdateProductRequest request) {
    ProductEntity productEntity = ProductEntity.builder()
        .id(request.getId())
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice())
        .category(request.getCategory())
        .build();
    productService.save(productEntity);
  }

  public void deleteByIdHandler(Integer id) throws DataNotFoundException {
    productService.deleteById(id);
  }

}
