package com.project.catcaring.dto.user.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {

  private final String username;
  private final String password;
}
