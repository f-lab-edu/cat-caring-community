package com.project.catcaring.dto.user.request;

import com.project.catcaring.domain.User;
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
  private final User.Location location;
}
