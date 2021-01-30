package com.baykalsoft.debtrack.service;


import com.baykalsoft.debtrack.assembler.DebtorAssembler;
import com.baykalsoft.debtrack.dto.DataTableFilterDTO;
import com.baykalsoft.debtrack.dto.DebtorSummaryDto;
import com.baykalsoft.debtrack.dto.ResultDto;
import com.baykalsoft.debtrack.entity.Debtor;
import com.baykalsoft.debtrack.enums.ResponseCode;
import com.baykalsoft.debtrack.repository.DebtorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DebtorService {

  private final Logger logger = LoggerFactory.getLogger(DebtorService.class);

  @Autowired
  private DebtorRepository debtorRepository;

  @Autowired
  private DebtorAssembler debtorAssembler;


  public Object getDebtorsSummary(DataTableFilterDTO filter, long clientId) throws Exception {

    try {
      logger.info("1");

      Page<Debtor> xx = debtorRepository.findDebtorsByClientId(filter.getPageable(), clientId);
      logger.info("2");

      Page<DebtorSummaryDto> debtorSummaryDtos = debtorRepository.findDebtorsByClientId(filter.getPageable(),clientId)
          .map(debtorAssembler::toSummaryDto);

      logger.info("3");

      if(debtorSummaryDtos.getSize() == 0)
        throw new Exception("there is not any debtor information for the client");

      logger.info("4");

      int total;
      if(debtorSummaryDtos.getSize() > debtorSummaryDtos.getTotalElements()) {
        total = (int)debtorSummaryDtos.getTotalElements();
      } else
        total = debtorSummaryDtos.getSize();

      logger.info("5");

      return ResultDto.BUILDER().response(ResponseCode.SUCCESS)
          .add("draw", Integer.valueOf(filter.getDraw()))
          .add("data", debtorSummaryDtos.getContent())
          .add("recordsTotal", total)
          .add("recordsFiltered", Long.valueOf(debtorSummaryDtos.getTotalElements()))
          .build();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }
}
