package com.spring.template.silver.app.usecase.utils;

import com.spring.template.silver.app.entrypoint.payload.product.ProductDto;
import com.spring.template.silver.app.infrastructure.entity.ProductEntity;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ModelMapperUtils {

  private final ModelMapper modelMapper;

  public ModelMapperUtils(ModelMapper modelMapper) {
    initMapperRule(modelMapper);
    this.modelMapper = modelMapper;
  }

  private void initMapperRule(ModelMapper modelMapper) {
    modelMapper.addMappings(new PropertyMap<ProductEntity, ProductDto>() {
      @Override
      protected void configure() {
        map().setCategory(source.getCategory().getName());
      }
    });

  }

  public <T> T map(Object source, Class<T> targetClass) {
    return modelMapper.map(source, targetClass);
  }

  public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    return source
        .stream()
        .map(element -> modelMapper.map(element, targetClass))
        .toList();
  }

}
