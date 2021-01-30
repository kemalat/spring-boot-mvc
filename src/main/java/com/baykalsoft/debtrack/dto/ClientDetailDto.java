package com.baykalsoft.debtrack.dto;

import com.baykalsoft.debtrack.entity.Comission;
import com.baykalsoft.debtrack.enums.ActionPlan;
import com.baykalsoft.debtrack.enums.ClientStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class ClientDetailDto {

  private long clientId;
  private LocalDateTime customerSince;
  private ClientStatus status;
//  private String assignee;
  private ActionPlan actionPlan;
  private String name;
  private String address;
  private String contact;
  private String email;
  private String phone;
  private String web;
  private int rateCalculation;
  Map<String, Object> accounts;
  Map<String, Object> recoveryRate;
  Map<String, Object> optionalFee;
  private List<Comission> comission;
  private List<String> agents;
  private List<String> statutes;
  private List<String> actionPlans;
  Map<String, Object> report;

}
