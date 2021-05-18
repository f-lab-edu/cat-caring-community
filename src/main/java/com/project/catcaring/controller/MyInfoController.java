package com.project.catcaring.controller;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserChangeRequest;
import com.project.catcaring.dto.user.response.UserInfoResponse;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/user")
public class MyInfoController {

  private final UserService userService;
  private final LoginSessionService loginSessionService;

  @GetMapping("/myInfo")
  public UserInfoResponse userInfo(HttpSession session) {
    String username = loginSessionService.getCurrentUserId(session);
    User userInfo = userService.getUserInfo(username);
    return new UserInfoResponse(userInfo);
  }

  @DeleteMapping("/delete")
  public HttpStatus deleteUser(HttpSession session) {
    String username = loginSessionService.getCurrentUserId(session);
    userService.deleteUser(username);
    log.info("회원 삭제 완료: " + username );
    loginSessionService.logoutUser(session);
    return HttpStatus.OK;
  }

  /**
   *  회원 정보 수정
   */
  @PatchMapping("/update")
  public HttpStatus userInfoUpdate(@RequestBody UserChangeRequest userChangeRequest, HttpSession session) {

    String username = loginSessionService.getCurrentUserId(session);
    if(username == null) {
      log.warn("회원가입이 필요한 기능입니다. ");
    } else {
      log.info(username + "의 회원 정보 변경을 시작합니다.");
    }

    userService.updateUserInfo(userChangeRequest, username);

    return HttpStatus.OK;

  }


}
