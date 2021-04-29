package com.project.catcaring.dto;

import com.project.catcaring.domain.user.LocationAdd;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateRequest {
  private String username;
  private String password;
  private String email;
  private String fullName;
  private LocationAdd location;

}
