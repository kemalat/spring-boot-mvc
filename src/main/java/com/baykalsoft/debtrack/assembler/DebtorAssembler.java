package com.baykalsoft.debtrack.assembler;


import com.baykalsoft.debtrack.dto.DebtorSummaryDto;
import com.baykalsoft.debtrack.entity.Debtor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DebtorAssembler {

  private final Logger logger = LoggerFactory.getLogger(DebtorAssembler.class);


  public DebtorSummaryDto toSummaryDto(Debtor debtor) {
    return DebtorSummaryDto.of(debtor.getId(),debtor.getStatus(),debtor.getName(),debtor.getCreatedDate(),
        debtor.getLastPaymentDate(),debtor.getOwing().doubleValue(),debtor.getPrincipal().doubleValue());
  }
}