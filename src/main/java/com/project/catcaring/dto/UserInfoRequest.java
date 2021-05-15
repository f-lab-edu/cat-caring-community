package com.project.catcaring.dto;

import com.project.catcaring.domain.user.User.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoRequest {
  private final String username;
  private final String password;
  private final String email;
  private final String fullName;
  private final Address location;

}
