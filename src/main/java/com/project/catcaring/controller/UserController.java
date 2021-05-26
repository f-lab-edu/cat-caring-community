package com.project.catcaring.controller;

import static com.project.catcaring.handler.HttpResponses.*;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserInfoRequest;
import com.project.catcaring.dto.user.UserLoginRequest;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.UserService;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

 private final UserService userService;
 private final LoginSessionService loginSessionService;

  @PostMapping
  public ResponseEntity<Void> signUp(@RequestBody @NonNull UserInfoRequest userInfoRequest) {
    userService.createUser(userInfoRequest);
    return RESPONSE_CREATED;
  }

  @PostMapping("/login")
  public ResponseEntity<Void> login(@RequestBody @NonNull UserLoginRequest userLoginRequest) {
      String username = userLoginRequest.getUsername();;
      String password = userLoginRequest.getPassword();

      Optional<User> user = userService.login(username, password);

      if(user.isEmpty()) {
        return RESPONSE_NOT_FOUND;
      } else {
        loginSessionService.loginUser(user.get().getId());
        log.info("로그인 완료: " + loginSessionService.getCurrentUserId());
        return RESPONSE_OK;
      }
  }

  @GetMapping("/logout")
  public ResponseEntity<Void> logout() {
    try {
      loginSessionService.logoutUser();
      return RESPONSE_OK;
    } catch (RuntimeException e) {
      return RESPONSE_UNAUTHORIZED;
    }
  }
}
