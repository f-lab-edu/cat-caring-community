package com.project.catcaring.controller;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.domain.user.User.Status;
import com.project.catcaring.dto.UserCreateRequest;
import com.project.catcaring.dto.UserLoginRequest;
import com.project.catcaring.service.LoginService;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.UserService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

 private final UserService userService;
 private final LoginSessionService loginSessionService;



  @RequestMapping(path = {"/signup"}, method = {RequestMethod.POST})
  public void signUp(@RequestBody @NonNull UserCreateRequest userCreateRequest) {
    userService.createUser(userCreateRequest);
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
        return HttpStatus.OK;
      }

  }

  @GetMapping("/")
  public void firstPage() {

  }

  @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET})
  public HttpStatus logout(HttpSession session) {
    loginSessionService.logoutUser(session);
    return HttpStatus.OK;
  }





}
