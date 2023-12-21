package com.spring.template.silver.app.usecase.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingEntityDto<T> {

  private Integer currentPage;
  private Long totalResults;
  private Integer totalPages;
  private List<T> results;

}
