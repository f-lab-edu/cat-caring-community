package com.project.catcaring.dto.user.response;

import com.project.catcaring.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoResponse {
  public final User user;

}
