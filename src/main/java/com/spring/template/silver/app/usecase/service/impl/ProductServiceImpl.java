package com.spring.template.silver.app.usecase.service.impl;

import com.spring.template.silver.app.infrastructure.dto.ProductInfo;
import com.spring.template.silver.app.infrastructure.entity.ProductEntity;
import com.spring.template.silver.app.infrastructure.jpa.ProductRepository;
import com.spring.template.silver.app.usecase.exception.DataNotFoundException;
import com.spring.template.silver.app.usecase.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<ProductEntity> getAll() {
    return productRepository.findAll();
  }

  @Override
  public ProductEntity getById(Integer id) throws DataNotFoundException {
    return productRepository.findById(id).orElseThrow(
        () -> new DataNotFoundException("Product not found!")
    );
  }

  @Override
  @Transactional
  public void save(ProductEntity productEntity) {
    productRepository.save(productEntity);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) throws DataNotFoundException {
    var product = getById(id);
    productRepository.delete(product);
  }

  @Override
  public ProductInfo getByIdV2(Integer id) throws DataNotFoundException {
    return productRepository.findById(id, ProductInfo.class).orElseThrow(
        () -> new DataNotFoundException("Product not found!")
    );
  }

}
