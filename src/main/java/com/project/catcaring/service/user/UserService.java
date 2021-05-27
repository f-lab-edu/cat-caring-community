package com.project.catcaring.service.user;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserInfoRequest;
import java.util.Optional;

public interface UserService {
  Optional<User> login(String username, String password);
  void createUser(UserInfoRequest userInfoRequest);
}
