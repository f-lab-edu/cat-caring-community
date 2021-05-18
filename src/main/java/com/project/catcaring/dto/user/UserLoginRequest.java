package com.project.catcaring.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {
  private final String username;
  private final String password;


}
