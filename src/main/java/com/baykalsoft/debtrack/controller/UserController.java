package com.baykalsoft.debtrack.controller;

import com.baykalsoft.debtrack.dto.ResultDto;
import com.baykalsoft.debtrack.dto.UserDto;
import com.baykalsoft.debtrack.enums.ResponseCode;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping(path = "/register/user",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> registerUser(@RequestBody UserDto user)
      throws Exception {
    logger.info("UserDto {}",user.toString());

    return ResponseEntity.ok(ResultDto.BUILDER().response(ResponseCode.SUCCESS));
  }

  @PostMapping(path = "/session/info",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> getSessionInfo(Principal principal) {
    logger.info("principal.getName(): "+principal.getName());

    return ResponseEntity.ok(ResultDto.BUILDER().response(ResponseCode.SUCCESS));
  }

}


