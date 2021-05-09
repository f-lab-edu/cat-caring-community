package com.project.catcaring.controller;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.UserCreateRequest;
import com.project.catcaring.dto.UserLoginRequest;
import com.project.catcaring.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.manager.util.SessionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

 private final UserService userService;



  @RequestMapping(path = {"/signup"}, method = {RequestMethod.GET , RequestMethod.POST})
  public void signUp(@RequestBody @NonNull UserCreateRequest userCreateRequest) {
    userService.createUser(userCreateRequest);
  }

  @RequestMapping(path = {"/login"}, method = {RequestMethod.GET})
  public HttpStatus login(@RequestBody @NonNull UserLoginRequest userLoginRequest) {
      String username = userLoginRequest.getUsername();;
      String password = userLoginRequest.getPassword();

      User user = userService.login(username, password);

      if(user == null) {
        return HttpStatus.NOT_FOUND;

      } else if(User.Status.MEMBER.equals(user.getStatus())) {
        return HttpStatus.OK;
      } else {
        throw new RuntimeException("login error! 유저 네임 혹은 비밀번호가 일치하지 않습니다.");
      }

  }

  @GetMapping("/")
  public void firstPage() {

  }


}
