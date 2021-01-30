package com.baykalsoft.debtrack.controller;

import com.baykalsoft.debtrack.dto.ResultDto;
import com.baykalsoft.debtrack.entity.Message;
import com.baykalsoft.debtrack.entity.User;
import com.baykalsoft.debtrack.enums.ResponseCode;
import com.baykalsoft.debtrack.repository.MessageRepository;
import com.baykalsoft.debtrack.repository.UserRepository;
import com.baykalsoft.debtrack.service.ClientService;
import com.baykalsoft.debtrack.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Ramesh Fadatare
 *
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeController
{
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    private final ClientService clientService;


    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("msgs", messageRepository.findAll());
        return "userhome";
    }

    @PostMapping("/messages")
    public String saveMessage(Message message)
    {
        messageRepository.save(message);
        return "redirect:/home";
    }

    @GetMapping("/clients")
    public String clientsPage(Model model, Principal principal) throws JsonProcessingException {
        logger.info("principal.getName(): "+principal.getName());

      userService.updateUserModel(principal.getName(),model);

      return "clients";
    }

    @GetMapping("/users")
    public String usersPage(Model model, Principal principal) {
        logger.info("principal.getName(): "+principal.getName());
        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
        if( optionalUser.isPresent())
            model.addAttribute("userName", optionalUser.get().getName());

        return "users";
    }

    @GetMapping("/profile")
    public String clientProfilePage(Model model, Principal principal) throws JsonProcessingException {
        logger.info("principal.getName(): "+principal.getName());

        userService.updateUserModel(principal.getName(),model);

//        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
//        if( optionalUser.isPresent())
//            model.addAttribute("userName", optionalUser.get().getName());


        return "profile";
    }

  @GetMapping("/edit_client")
  public String editClientPage(Model model, Principal principal) throws JsonProcessingException {
      logger.info("principal.getName(): "+principal.getName());

    userService.updateUserModel(principal.getName(),model);

//      Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
//      if( optionalUser.isPresent())
//          model.addAttribute("userName", optionalUser.get().getName());

    return "edit_client";
  }

    @GetMapping("/edit_user")
    public String editUserPage(Model model, Principal principal) {
        logger.info("principal.getName(): "+principal.getName());
        Optional<User> optionalUser = userRepository.findByEmail(principal.getName());
        if( optionalUser.isPresent())
            model.addAttribute("userName", optionalUser.get().getName());

        return "edit_user";
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> registerUser(@RequestParam Map<String, String> body) throws Exception {
            logger.info("UserDto {}",body.toString());
            return ResponseEntity.ok(userService.createUser(body));
    }

    @PostMapping(value = "/updateUser")
    public ResponseEntity<Object> updateUser(@RequestParam Map<String, String> body) throws Exception {
        logger.info("updateUser {}",body.toString());
        userService.updateUser(body);
      return ResponseEntity.ok(ResultDto.BUILDER().response(ResponseCode.SUCCESS).build());
    }

    }

