package com.baykalsoft.debtrack.assembler;


import com.baykalsoft.debtrack.dto.ClientDetailDto;
import com.baykalsoft.debtrack.dto.ClientSummaryDto;
import com.baykalsoft.debtrack.entity.Client;
import com.baykalsoft.debtrack.entity.ClientReport;
import com.baykalsoft.debtrack.enums.ActionPlan;
import com.baykalsoft.debtrack.enums.ClientStatus;
import com.baykalsoft.debtrack.enums.DebtorStatus;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientAssembler {

  private final Logger logger = LoggerFactory.getLogger(ClientAssembler.class);


  public ClientSummaryDto toSummaryDto(Client client) {
    double totalOwing = client.getDebtors().stream().filter(o -> o.getOwing().doubleValue() > 0)
        .mapToDouble(o -> o.getOwing().doubleValue()).sum();
    double totalPrincipal = client.getDebtors().stream().filter(o -> o.getPrincipal().doubleValue() > 0)
        .mapToDouble(o -> o.getPrincipal().doubleValue()).sum();
    long totalAccounts = client.getDebtors().stream().filter(o -> o.getStatus() == DebtorStatus.ACTIVE).count();
    int rate = (int)Math.round((totalOwing / totalPrincipal) *100);
    LocalDateTime localDateTime = LocalDateTime.now();
    return ClientSummaryDto
        .of(client.getId(), client.getName(), client.getCreatedDate(), client.getStatus(), totalAccounts, totalOwing, rate, localDateTime);
  }

  public ClientDetailDto toDetailDto(Client client, ClientReport clientReport) {

    Map<String, Object> accounts = new LinkedHashMap();
    Map<String, Object> recoveryRate = new LinkedHashMap();
    Map<String, Object> optionalFee = new LinkedHashMap();
    Map<String, Object> report = new LinkedHashMap();

    long total = client.getDebtors().stream().count();
    long active = client.getDebtors().stream().filter(o -> o.getStatus() == DebtorStatus.ACTIVE).count();
    long closed = client.getDebtors().stream().filter(o -> o.getStatus() == DebtorStatus.CLOSED).count();

    accounts.put("total",total);
    accounts.put("active",active);
    accounts.put("closed",closed);

    double totalOwing = clientReport.getOwingActive().doubleValue() + clientReport.getOwingClosed().doubleValue();
    recoveryRate.put("collected",Math.round( ((clientReport.getPrincipal().doubleValue() - totalOwing) /clientReport.getPrincipal().doubleValue()) *100 ) );
    recoveryRate.put("owing",Math.round( ((totalOwing /clientReport.getPrincipal().doubleValue()) *100 ) ));

    if(client.getOptionalFee() == null ) {
      optionalFee.put("applied", false);
    }else {
      optionalFee.put("party", client.getOptionalFee().getFeeParty().ordinal());
      optionalFee.put("percent", client.getOptionalFee().getPercent());
      optionalFee.put("applied", true);
    }

    report.put("principal",clientReport.getPrincipal());
    report.put("interest",clientReport.getInterest());
    report.put("fees",clientReport.getFees());
    report.put("legalFees",clientReport.getLegalFees());
    report.put("owingActive",clientReport.getOwingActive().doubleValue());
    report.put("owingClosed",clientReport.getOwingClosed().doubleValue());
    report.put("owingAll",totalOwing);
    report.put("comission",0);

    List<String> agents = client.getUsers().stream().map(user -> user.getName()).collect(Collectors.toList());
    List<String> statutes = Arrays.stream(ClientStatus.values()).map(clientStatus -> clientStatus.name()).collect(Collectors.toList());

    List<String> actionPlans = Arrays.stream(ActionPlan.values()).map(actionPlan -> actionPlan.name()).collect(Collectors.toList());

    ClientDetailDto  clientDetailDto = ClientDetailDto.of(client.getId(),client.getCreatedDate(),client.getStatus(),
        client.getActionPlan(),client.getName(),client.getAddress(),
        client.getContact().getFullName(),client.getContact().getEmail(),client.getContact().getPhone(),
        client.getWebSite(),client.getRateCalculationMode(),
        accounts,
        recoveryRate,
        optionalFee,
        client.getComissions(),agents,statutes,actionPlans,report);

    return clientDetailDto;
  }

//  private String toAgentDto(User user) {
//    return user.getName();
//  }

//  public ClientDetailDto.Comissions toComission(Comission commission) {
//
//    ClientDetailDto.Comissions.of(commission.getPercent(),commission.getComissionItem().ordinal());
//    client.getComissions().stream().map(this::toComission).collect(Collectors.toList());
//
//    return null;
//  }
}
