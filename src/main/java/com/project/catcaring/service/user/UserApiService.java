package com.project.catcaring.service.user;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserChangeRequest;

public interface UserApiService {
  User getUserInfo(Long userId);
  void deleteUser(Long userId);
  void updateUserInfo(UserChangeRequest userChangeRequest, Long userId);
}
