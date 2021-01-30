package com.baykalsoft.debtrack.dto;



import com.baykalsoft.debtrack.enums.ClientStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class ClientSummaryDto {

  private long id;
  private String name;
  private LocalDateTime customerSince;
  private ClientStatus status;
  private long totalAccounts;
  private double totalOwing;
  private int recoveryRate;
  private LocalDateTime lastInvoiceDate;
;
}
