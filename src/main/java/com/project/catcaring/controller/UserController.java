package com.project.catcaring.controller;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserInfoRequest;
import com.project.catcaring.dto.user.UserLoginRequest;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.UserService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/auth")
public class UserController {

 private final UserService userService;
 private final LoginSessionService loginSessionService;



  @RequestMapping(path = {"/signup"}, method = {RequestMethod.POST})
  public HttpStatus signUp(@RequestBody @NonNull UserInfoRequest userInfoRequest) {
    userService.createUser(userInfoRequest);
    return HttpStatus.OK;
  }

  @RequestMapping(path = {"/login"},method = {RequestMethod.POST})
  public HttpStatus login(@RequestBody @NonNull UserLoginRequest userLoginRequest, HttpSession session) {
      String username = userLoginRequest.getUsername();;
      String password = userLoginRequest.getPassword();

      Optional<User> user = userService.login(username, password);

      if(user.isEmpty()) {
        return HttpStatus.NOT_FOUND;

      } else {
        loginSessionService.loginUser(session, user.get().getUsername());
        log.info("로그인 완료: " + session.getAttribute("USER_ID"));
        return HttpStatus.OK;
      }

  }

  @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
  public HttpStatus logout(HttpSession session) {
    loginSessionService.logoutUser(session);
    log.info("로그아웃 완료: " +session.getAttribute("USER_ID"));
    return HttpStatus.OK;
  }





}
