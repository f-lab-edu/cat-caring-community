package com.project.catcaring.controller;

import static com.project.catcaring.error.HttpResponses.RESPONSE_OK;

import com.project.catcaring.aop.annotation.CheckLoginAndResult;
import com.project.catcaring.domain.User;
import com.project.catcaring.dto.user.request.UserChangeRequest;
import com.project.catcaring.service.user.LoginSessionService;
import com.project.catcaring.service.user.UserApiServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {

  private final UserApiServiceImpl userServiceImpl;
  private final LoginSessionService loginSessionService;

  @GetMapping
  @CheckLoginAndResult
  public ResponseEntity<User> getUserInfo() {
    return ResponseEntity.ok(userServiceImpl.getUserInfo(loginSessionService.getCurrentUserId()));
  }

  @DeleteMapping
  @CheckLoginAndResult
  public ResponseEntity<String> deleteUser() {
      userServiceImpl.deleteUser(loginSessionService.getCurrentUserId());
      loginSessionService.logoutUser();
      return RESPONSE_OK;
  }

  @PatchMapping
  @CheckLoginAndResult
  public ResponseEntity<String> updateUser(@RequestBody UserChangeRequest userChangeRequest) {
      userServiceImpl.updateUser(userChangeRequest, loginSessionService.getCurrentUserId());
    return RESPONSE_OK;
  }
}
