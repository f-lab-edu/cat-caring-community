package com.project.catcaring.dto;

import com.project.catcaring.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginResponse {

  enum LoginStatus {
    SUCCESS, FAIL, DELETED
  }
  @NonNull
  private LoginStatus status;
  private User user;

  public static LoginResponse fail() {
    return new LoginResponse(LoginStatus.FAIL);
  }

  public static LoginResponse success (User user) {
    return new LoginResponse(LoginStatus.SUCCESS, user);
  }




}
