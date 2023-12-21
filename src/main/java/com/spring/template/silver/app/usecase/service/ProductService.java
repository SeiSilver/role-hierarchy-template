package com.spring.template.silver.app.usecase.service;

import com.spring.template.silver.app.infrastructure.dto.ProductInfo;
import com.spring.template.silver.app.infrastructure.entity.ProductEntity;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;

import java.util.List;

public interface ProductService {

  List<ProductEntity> getAll();

  ProductEntity getById(Integer id) throws DataNotFoundException;

  ProductInfo getByIdV2(Integer id) throws DataNotFoundException;

  void save(ProductEntity productEntity);

  void deleteById(Integer id) throws DataNotFoundException;

}
