package com.baykalsoft.debtrack.service;


import com.baykalsoft.debtrack.assembler.ClientAssembler;
import com.baykalsoft.debtrack.assembler.UserAssembler;
import com.baykalsoft.debtrack.dto.ClientDetailDto;
import com.baykalsoft.debtrack.dto.ClientSummaryDto;
import com.baykalsoft.debtrack.dto.DataTableFilterDTO;
import com.baykalsoft.debtrack.dto.ResultDto;
import com.baykalsoft.debtrack.entity.Client;
import com.baykalsoft.debtrack.entity.ClientReport;
import com.baykalsoft.debtrack.entity.User;
import com.baykalsoft.debtrack.enums.ResponseCode;
import com.baykalsoft.debtrack.repository.ClientReportRepository;
import com.baykalsoft.debtrack.repository.ClientRepository;
import com.baykalsoft.debtrack.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {

  private final Logger logger = LoggerFactory.getLogger(ClientService.class);

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientAssembler clientAssembler;

  @Autowired
  private ClientReportRepository clientReportRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserAssembler userAssembler;



  public Object getClientsSummary(DataTableFilterDTO filter) throws Exception {

    try {
      Page<ClientSummaryDto> clientDetailDtos = clientRepository.findAll(filter.getPageable())
          .map(clientAssembler::toSummaryDto);

      if(clientDetailDtos.getSize() == 0)
        throw new Exception("there is not any client information");

      int total;
      if(clientDetailDtos.getSize() > clientDetailDtos.getTotalElements())
        total = (int)clientDetailDtos.getTotalElements();
      else
        total = (int)clientDetailDtos.getSize();

      return ResultDto.BUILDER().response(ResponseCode.SUCCESS)
          .add("draw", Integer.valueOf(filter.getDraw()))
          .add("data", clientDetailDtos.getContent())
          .add("recordsTotal", total)
          .add("recordsFiltered", Long.valueOf(clientDetailDtos.getTotalElements()))
          .build();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }

  public ClientDetailDto getClientDetail(int clientId) throws Exception {

    try {
      Optional<Client> optionalClient  = clientRepository.findById((long)clientId);
      Client client = optionalClient.isPresent() == true ? optionalClient.get() : null;
      if (client == null)
        throw new Exception("No client data found for client id :"+clientId);

      Optional<ClientReport> optClientReport = clientReportRepository.findByClientId(clientId);
      ClientReport clientReport = optClientReport.isPresent() == true ? optClientReport.get() : null;
      if (clientReport == null)
        throw new Exception("No client report data found for client id :"+clientId);

      logger.info(client.toString());
      return clientAssembler.toDetailDto(client,clientReport);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }

  public Object getClientsSummary(String userId, DataTableFilterDTO filter) throws Exception {

    try {
      Optional<User> userOptional = userRepository.findById(Integer.valueOf(userId));
      User user = userOptional.isPresent() == true ? userOptional.get() : null;
      if (user == null)
        throw new Exception("No User data found for client id :"+userId);


      List<ClientSummaryDto> clientSummaryDtos = user.getClients().stream().map(clientAssembler::toSummaryDto).collect(Collectors.toList());;

      if(clientSummaryDtos.size() == 0)
        throw new Exception("there is not any client information");

        int total = (int)clientSummaryDtos.size();

      return ResultDto.BUILDER().response(ResponseCode.SUCCESS)
          .add("draw", Integer.valueOf(filter.getDraw()))
          .add("data", clientSummaryDtos)
          .add("recordsTotal", total)
          .add("recordsFiltered", Long.valueOf(clientSummaryDtos.size()))
          .build();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }

}
