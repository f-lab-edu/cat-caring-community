package com.project.catcaring.service.user;

import com.project.catcaring.domain.User;
import com.project.catcaring.dto.user.request.UserChangeRequest;

public interface UserApiService {

  User getUserInfo(Long userId);
  void deleteUser(Long userId);
  void updateUser(UserChangeRequest userChangeRequest, Long userId);
}
