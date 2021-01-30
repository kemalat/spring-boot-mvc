package com.baykalsoft.debtrack.dto;

import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
public class FilterDto {

  private int draw;
  private int start;
  private int length;
  private String orderColumn;
  private String orderDirection;
  private String searchValue;

  public DataTableFilterDTO getFilter(HttpServletRequest request) {

    return DataTableFilterDTO.builder()
        .draw(this.draw)
        .start(this.start)
        .length(this.length)
        .orderColumn(orderColumn == null ? request.getParameter("order[0][column]") : orderColumn)
        .orderDirection(orderDirection == null ? request.getParameter("order[0][dir]") : orderDirection)
        .searchValue(searchValue == null ? request.getParameter("search[value]") : searchValue)
        .pageable(PageRequest.of(this.start / this.length,
            this.length,
            this.orderDirection == null || this.orderDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
            this.orderColumn))
        .build();
  }
}