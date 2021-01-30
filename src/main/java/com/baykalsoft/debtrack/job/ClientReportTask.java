package com.baykalsoft.debtrack.job;


import com.baykalsoft.debtrack.entity.ClientReport;
import com.baykalsoft.debtrack.repository.ClientReportRepository;
import com.baykalsoft.debtrack.repository.ClientRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClientReportTask {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientReportRepository clientReportRepository;

  private static final Logger log = LoggerFactory.getLogger(ClientReportTask.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


  @Scheduled(fixedRateString = "${report.thread.schedule}")
  public void reportCurrentTime() {

    log.info("The time is now {}", dateFormat.format(new Date()));

    clientRepository.getIds().forEach(id -> {
      Object[] reportData = clientRepository.getTotals(id.intValue()).get(0);
      BigDecimal fees = reportData[0] == null ? BigDecimal.ZERO : (BigDecimal)reportData[0];
      BigDecimal interest = reportData[1] == null ? BigDecimal.ZERO : (BigDecimal)reportData[1];
      BigDecimal legalFees = reportData[2] == null ? BigDecimal.ZERO : (BigDecimal)reportData[2];
      BigDecimal principal = reportData[3] == null ? BigDecimal.ZERO : (BigDecimal)reportData[3];

      BigDecimal owingActive = clientRepository.getOwingActive(id.intValue()).get(0) == null ? BigDecimal.ZERO : clientRepository.getOwingActive(id.intValue()).get(0);
      BigDecimal owingClosed = clientRepository.getOwingClosed(id.intValue()).get(0)== null ? BigDecimal.ZERO : clientRepository.getOwingClosed(id.intValue()).get(0);


      if(clientReportRepository.findByClientId(id.intValue()).isPresent()) {
        ClientReport report = ClientReport.builder().id(id.intValue()).fees(fees).clientId(id.intValue()).interest(interest)
            .legalFees(legalFees).principal(principal).owingActive(owingActive).owingClosed(owingClosed).build();

        clientReportRepository.updateClientReport(report.getFees(),report.getInterest(),report.getLegalFees(),report.getOwingActive(),report.getOwingClosed(),report.getPrincipal(),id.intValue());
      }
      else {
        ClientReport report = ClientReport.builder().id(id.intValue()).fees(fees).clientId(id.intValue()).interest(interest)
            .legalFees(legalFees).principal(principal).owingActive(owingActive).owingClosed(owingClosed).build();

        clientReportRepository.save(report);
      }
    });

  }


}
