package com.baykalsoft.debtrack.dto;


import com.baykalsoft.debtrack.enums.DebtorStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class DebtorSummaryDto {

  private long accountId;
  private DebtorStatus status;
  private String name;
  private LocalDateTime created;
  private LocalDateTime lastPaymentDate;
  private double owing;
  private double principal;


}
