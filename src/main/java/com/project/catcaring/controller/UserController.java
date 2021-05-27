package com.project.catcaring.controller;

import static com.project.catcaring.handler.HttpResponses.*;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserInfoRequest;
import com.project.catcaring.dto.user.UserLoginRequest;
import com.project.catcaring.handler.LoginErrorException;
import com.project.catcaring.service.user.LoginSessionService;
import com.project.catcaring.service.user.UserServiceImpl;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class UserController {

 private final UserServiceImpl userServiceImpl;
 private final LoginSessionService loginSessionService;

  @PostMapping
  public ResponseEntity<String> signUp(@RequestBody @NonNull UserInfoRequest userInfoRequest) {
    userServiceImpl.createUser(userInfoRequest);
    return RESPONSE_CREATED;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @NonNull UserLoginRequest userLoginRequest) {
    Optional<User> user = userServiceImpl.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
    if(user.isPresent()) {
      loginSessionService.loginUser(user.get().getId());
      return new ResponseEntity<>("LOGIN COMPLETED", HttpStatus.OK);
    }
    throw new LoginErrorException(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/logout")
  public ResponseEntity<String> logout() {
    try {
      loginSessionService.logoutUser();
      return new ResponseEntity<>("LOGOUT COMPLETED", HttpStatus.OK);
    } catch (RuntimeException e) {
      return RESPONSE_UNAUTHORIZED;
    }
  }
}
