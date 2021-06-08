package com.project.catcaring.service.user;

import com.project.catcaring.domain.User;
import com.project.catcaring.dto.user.request.UserInfoRequest;
import java.util.Optional;

public interface UserService {

  Optional<User> login(String username, String password);
  void createUser(UserInfoRequest userInfoRequest);
}
