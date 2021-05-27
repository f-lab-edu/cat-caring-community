package com.project.catcaring.service.user;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserInfoRequest;
import com.project.catcaring.handler.DuplicateIdException;
import com.project.catcaring.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  public void createUser(UserInfoRequest userInfoRequest) {
    if(isUniqueId(userInfoRequest.getUsername())) {
      throw new DuplicateIdException();
    }
    userMapper.insertUser(User.generate(userInfoRequest));
  }

  @Transactional(readOnly = true)
  public boolean isUniqueId(String username) {
    return userMapper.isUniqueId(username);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<User> login (String username, String password) {
    Optional<User> currentUser = Optional.ofNullable(userMapper.findByUsername(username));
    if(currentUser.isPresent()) {
      boolean checkPassword = BCrypt.checkpw(password, currentUser.get().getPassword());
      if(checkPassword) return currentUser;
    }
    return Optional.empty();
  }
}
