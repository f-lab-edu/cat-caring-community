package com.project.catcaring.dto.user;

import com.project.catcaring.domain.user.User.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangeRequest {
  private final String password;
  private final String fullName;
  private final Address location;

}
