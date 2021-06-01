package com.project.catcaring.dto.user;

import com.project.catcaring.domain.user.User.Address;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangeRequest {
  @NonNull
  private final String password;
  @NonNull
  private final String fullName;
  @NonNull
  private final Address location;

}
