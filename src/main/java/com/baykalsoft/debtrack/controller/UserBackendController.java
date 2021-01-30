package com.baykalsoft.debtrack.controller;

import com.baykalsoft.debtrack.dto.ClientDetailDto;
import com.baykalsoft.debtrack.dto.FilterDto;
import com.baykalsoft.debtrack.service.ClientService;
import com.baykalsoft.debtrack.service.DebtorService;
import com.baykalsoft.debtrack.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserBackendController {

  private final Logger logger = LoggerFactory.getLogger(UserBackendController.class);

  private final ClientService clientService;
  private final DebtorService debtorService;
  private final UserService userService;


  @PostMapping(path = "/notifications",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getTasks(@RequestBody MultiValueMap<String, String> formData) throws Exception {
    logger.info("status {}",formData);
    return ResponseEntity.ok(userService.getUsers());
  }


  @PostMapping(path = "/client/list",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getClients(FilterDto filter, @RequestBody MultiValueMap<String, String> formData,  Principal principal,HttpServletRequest request) throws Exception {
    logger.info("principal.getName(): "+principal.getName());
    logger.info("param: "+formData);

    return ResponseEntity.ok(clientService.getClientsSummary(formData.get("userId").get(0),filter.getFilter(request)));
//    return ResponseEntity.ok(ResultDto.BUILDER().response(ResponseCode.SUCCESS).build());
  }

  @PostMapping(path = "/client/detail/{clientId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDetailDto getClientDetail(@PathVariable("clientId") int clientId) throws Exception {
    return clientService.getClientDetail(clientId);
  }

  @PostMapping(path = "/debtor/list/{clientId}",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getDebtorsByClient(@PathVariable("clientId") long clientId,FilterDto filter, HttpServletRequest request)
      throws Exception {
    logger.info("filter {}",filter.toString());
    logger.info("id {}",clientId);
    logger.info("pageable {}",filter.getFilter(request));

    return ResponseEntity.ok(debtorService.getDebtorsSummary(filter.getFilter(request),clientId));
  }

  @GetMapping(path = "/debtor/detail/{debtorId}",produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDebtor(@PathVariable("debtorId") int debtorId) {

    return "{\n"
        + "  \"name\": \"Spare Parters\",\n"
        + "  \"accountId\": \"Jafar Cabbarli 44 AZ2101\",\n"
        + "  \"principal\": \"Kemal Atik\",\n"
        + "  \"owing\": \"kemal@spareparters.com\",\n"
        + "  \"status\": \"90532436633\"\n"
        + "}    ";

  }








//  @ExceptionHandler(Exception.class)
//  public ResponseDto handleException(Exception ex) {
//    return ResponseDto.of(false, 500, ex.getMessage());
//  }
//  @GetMapping(path = "/client/list",produces = MediaType.APPLICATION_JSON_VALUE)
//  public String getClients() {
//     return "{\n"
//         + "\t \"draw\": 1,\n"
//         + "\t \"recordsTotal\": 12,\n"
//         + "\t \"recordsFiltered\": 12,\n"
//         + "\t \"data\": [\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     },\n"
//         + "\t     {\n"
//         + "\t         \"id\": 1,\n"
//         + "\t         \"name\": \"Spare Parters\",\n"
//         + "\t         \"customerSince\": \"2011\\\\/04\\\\/25\",\n"
//         + "\t         \"status\": \"AMC\",\n"
//         + "\t         \"totalAccounts\": \"98\",\n"
//         + "\t         \"totalOwing\": \"$32,800\",\n"
//         + "\t         \"recoveryRate\": \"%10\",\n"
//         + "\t         \"lastInvoiceDate\": \"2019\\\\/04\\\\/25\",\n"
//         + "\t         \"#\": \"\"\n"
//         + "\t     }\n"
//         + "\t ]\n"
//         + "}";
//  }

  //  @PostMapping(path = "/client/detail/{clientId}",produces = MediaType.APPLICATION_JSON_VALUE)
//  public String getClient(@PathVariable("clientId") int clientId) {
//
//    return "{\n"
//        + "  \"clientId\": 1,\n"
//        + "  \"status\": \"CBR\",\n"
//        + "  \"assignee\": \"ACC-Kemal Atik\",\n"
//        + "  \"actionPlan\": \"Phone to Debtor\",\n"
//        + "  \"name\": \"Spare Parters\",\n"
//        + "  \"addr\": \"Trend   13A213 34200\",\n"
//        + "  \"city\": \"Istanbul\",\n"
//        + "  \"contact\": \"Emir Atik\",\n"
//        + "  \"email\": \"emir@atik.com\",\n"
//        + "  \"phone\": \"+905252\",\n"
//        + "  \"web\": \"spareparters.com\",\n"
//        + "  \"rateCalculation\" : 1,\n"
//        + "  \"chargeNewAccount\" : true,\n"
//        + "  \"accounts\" :{\n"
//        + "  \t\"total\" : \"100\",\n"
//        + "  \t\"active\"  : \"30\",\n"
//        + "  \t\"closed\"  : \"70\"\n"
//        + "  },\n"
//        + "  \"recoveryRate\" :{\n"
//        + "  \t\"collected\" : \"75\",\n"
//        + "  \t\"totalOwing\"  : \"25\"\n"
//        + "  },\n"
//        + "  \n"
//        + "  \"chargedParty\" :{\n"
//        + "  \t\"party\" : \"1\",\n"
//        + "  \t\"rate\"  : \"25\"\n"
//        + "  },\n"
//        + "  \"comissions\": [\n"
//        + "    {\n"
//        + "      \"percent\": \"12\",\n"
//        + "      \"toPrincipal\" : true,\n"
//        + "      \"toFees\" : false,\n"
//        + "      \"toLegalFees\" : false,\n"
//        + "      \"toOther\" : false\n"
//        + "    }\n"
//        + "  ] \n"
//        + "}  ";
//
//  }

  //  @GetMapping(path = "/debtor/list/{clientId}",produces = MediaType.APPLICATION_JSON_VALUE)
//  public String getDebtorsByClient(@PathVariable("clientId") int clientId) {
//    return "{\n"
//        + "  \"draw\": 1,\n"
//        + "  \"recordsTotal\": 2,\n"
//        + "  \"recordsFiltered\": 2,\n"
//        + "  \"data\": [\n"
//        + "    {\n"
//            + "  \"name\": \"Baykal Atik\",\n"
//            + "  \"accountId\": \"773833\",\n"
//            + "  \"principal\": \"$62,37\",\n"
//            + "  \"owing\": \"$22,67\",\n"
//            + "  \"status\": \"CBR\"\n"
//        + "    },\n"
//        + "    {\n"
//            + "  \"name\": \"Emir Atik\",\n"
//            + "  \"accountId\": \"635252\",\n"
//            + "  \"principal\": \"$130,52\",\n"
//            + "  \"owing\": \"$130,52\",\n"
//            + "  \"status\": \"CBR\"\n"
//        + "    }\n"
//        + "  ]\n"
//        + "}    ";
//  }

}


