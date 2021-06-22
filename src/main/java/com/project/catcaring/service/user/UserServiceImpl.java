package com.project.catcaring.service.user;

import com.project.catcaring.domain.User;
import com.project.catcaring.dto.user.request.UserInfoRequest;
import com.project.catcaring.error.DuplicateIdException;
import com.project.catcaring.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  public void createUser(UserInfoRequest userInfoRequest) {
    if (isUniqueUsername(userInfoRequest.getUsername())) {
      throw new DuplicateIdException();
    }
    userMapper.insertUser(User.generate(userInfoRequest));
  }

  @Transactional(readOnly = true)
  boolean isUniqueUsername(String username) {
    return userMapper.isUniqueUsername(username);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> login (String username, String password) {
    Optional<User> currentUser = Optional.ofNullable(userMapper.findByUsername(username));
    return checkPassword(password, currentUser);
  }

  private Optional<User> checkPassword(String password, Optional<User> currentUser) {
    if (currentUser.isPresent()) {
      boolean comparePassword = BCrypt.checkpw(password, currentUser.get().getPassword());
      if (comparePassword) {
        return currentUser;
      }
    }
    return Optional.empty();
  }
}
