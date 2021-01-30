package com.baykalsoft.debtrack.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@Builder
public class DataTableFilterDTO {

  private int draw;
  private int start;
  private int length;
  private String orderColumn;
  private String orderDirection;
  private String searchValue;
  private Pageable pageable;
}