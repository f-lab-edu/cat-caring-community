package com.project.catcaring.controller;

import static com.project.catcaring.handler.HttpResponses.*;

import com.project.catcaring.aop.annotation.CheckLogin;
import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.request.UserInfoRequest;
import com.project.catcaring.dto.user.request.UserLoginRequest;
import com.project.catcaring.handler.LoginErrorException;
import com.project.catcaring.service.user.LoginService;
import com.project.catcaring.service.user.UserService;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

 private final UserService userServiceImpl;
 private final LoginService loginSessionService;

  @PostMapping
  public ResponseEntity<String> signUp(@RequestBody @NonNull UserInfoRequest userInfoRequest) {
    userServiceImpl.createUser(userInfoRequest);
    return RESPONSE_CREATED;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @NonNull UserLoginRequest userLoginRequest) {
    Optional<User> user = userServiceImpl.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
    if (user.isPresent()) {
      loginSessionService.loginUser(user.get().getId());
      return RESPONSE_OK;
    }
    throw new LoginErrorException(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/logout")
  @CheckLogin
  public ResponseEntity<String> logout() {
    loginSessionService.logoutUser();
    return RESPONSE_OK;
  }
}
