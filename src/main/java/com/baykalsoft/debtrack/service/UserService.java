package com.baykalsoft.debtrack.service;

import com.baykalsoft.debtrack.assembler.UserAssembler;
import com.baykalsoft.debtrack.dto.DataTableFilterDTO;
import com.baykalsoft.debtrack.dto.ResultDto;
import com.baykalsoft.debtrack.dto.UserDetailDto;
import com.baykalsoft.debtrack.dto.UserDto;
import com.baykalsoft.debtrack.entity.User;
import com.baykalsoft.debtrack.enums.ResponseCode;
import com.baykalsoft.debtrack.enums.UserStatus;
import com.baykalsoft.debtrack.repository.ClientRepository;
import com.baykalsoft.debtrack.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private UserAssembler userAssembler;

  @Autowired
  private ObjectMapper objectMapper;

  public ResultDto createUser(Map<String, String> body) {

    try {
      if (userRepository.findByEmail(body.get("email")).isPresent())
        return ResultDto.BUILDER().response(ResponseCode.ALREADY_REGISTERED);

      String crypted = bCryptPasswordEncoder.encode(body.get("password"));

      User user = User.builder().email(body.get("email")).password(crypted).name(body.get("name")).
          createdDate(LocalDateTime.now()).status(UserStatus.PENDING).build();
      userRepository.save(user);
      int id = userRepository.findByEmail(user.getEmail()).get().getId();
      userRepository.insertUserRole(id, 3);

      return ResultDto.BUILDER().response(ResponseCode.SUCCESS);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultDto.BUILDER().response(ResponseCode.SERVER_ERROR).add("error", e.getMessage());

    }

  }

  public Object getUsers(DataTableFilterDTO filter) throws Exception {
    try {

      List<UserDto> userDtos = userRepository.findUsers(filter).stream().map(userAssembler::toUserDto).collect(Collectors.toList());
      userDtos.removeIf(userDto -> userDto.getName().equalsIgnoreCase("Admin"));
      long colCount = userRepository.getColumnCount();


      return ResultDto.BUILDER().response(ResponseCode.SUCCESS)
          .add("draw", Integer.valueOf(filter.getDraw()))
          .add("data", userDtos)
          .add("recordsTotal", colCount)
          .add("recordsFiltered", colCount)
          .build();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }


  public Object getUsers() {
    try {
      List<User> users = userRepository.findByStatus(UserStatus.valueOf("PENDING"));

      return ResultDto.BUILDER().response(ResponseCode.SUCCESS)
          .add("messageCount", 1)
          .add("message", "There is " + users.size() + " pending user confirmations")
          .build();

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public UserDetailDto getUserDetail(int userId) throws Exception {

    try {
      Optional<User> optionalUser = userRepository.findById(userId);
      User user = optionalUser.isPresent() == true ? optionalUser.get() : null;
      if (user == null)
        throw new Exception("No user data found for client id :" + userId);
      logger.info("user:"+user.getId());
      List<String> clients = user.getClients().stream().map(client -> client.getName()).collect(Collectors.toList());
//      logger.info(""+clientRepository.findByAgent_Id(user.getId()));
//      List<String> clients = clientRepository.findByAgent_Id(user.getId()).stream().map(client -> client.getName()).collect(Collectors.toList());
      logger.info(user.toString());
      return userAssembler.toDetailDto(user,clients);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }

  }

  public ResultDto updateUser(Map<String, String> body) throws Exception {

    logger.info("body:"+body);
    String email = body.get("userEmail");
    Optional<User> optionalUser = userRepository.findByEmail(email);
    User user = optionalUser.isPresent() == true ? optionalUser.get() : null;
    if (user == null)
      throw new Exception("No user data found for id :" + email);
    user.setStatus(UserStatus.valueOf(body.get("status")));
    userRepository.save(user);
    return ResultDto.BUILDER().response(ResponseCode.SUCCESS);

  }

  public Model updateUserModel(String name, Model model) throws JsonProcessingException {

    Optional<User> optionalUser = userRepository.findByEmail(name);
    String json;
    User user = null;
    if( optionalUser.isPresent()) {
      user = optionalUser.get();
      UserDto userDto = userAssembler.toUserDto(user);
      ResultDto resultDto = ResultDto.BUILDER().response(ResponseCode.SUCCESS).add("user",userDto);
      json = objectMapper.writeValueAsString(resultDto);

    }
    else {
      ResultDto resultDto = ResultDto.BUILDER().response(ResponseCode.NOT_FOUND);
      json = objectMapper.writeValueAsString(resultDto);
    }

    model.addAttribute("result", json);
    model.addAttribute("userName", name);
    model.addAttribute("photo",user.getPhoto());

    return model;
  }
}
