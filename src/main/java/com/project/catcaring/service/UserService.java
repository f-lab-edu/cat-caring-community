package com.project.catcaring.service;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User;
import com.project.catcaring.domain.user.User.Status;
import com.project.catcaring.dto.user.UserInfoRequest;
import com.project.catcaring.handler.DuplicateIdException;
import com.project.catcaring.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserMapper userMapper;
  private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


  public void createUser(UserInfoRequest userInfoRequest) {
    if(isUniqueId(userInfoRequest.getUsername())) {
      throw new DuplicateIdException();
    }

    String rawPassword = userInfoRequest.getPassword();
    User encryptedUserInfo = User.builder().username(userInfoRequest.getUsername())
        .password(PASSWORD_ENCODER.encode(rawPassword))
        .fullName(userInfoRequest.getFullName())
        .email(userInfoRequest.getEmail())
        .location(userInfoRequest.getLocation())
        .authorityCode(Authority.USER)
        .status(Status.MEMBER)
        .build();

    userMapper.insertUser(encryptedUserInfo);
  }

  public boolean isUniqueId(String username) {
    return userMapper.isUniqueId(username);
  }

  @Transactional(readOnly = true)
  public Optional<User> login (String username, String password) {
    Optional<User> currentUser = Optional.ofNullable(userMapper.findByUsername(username));
    if(currentUser.isEmpty()) return Optional.empty();
    boolean checkPassword = BCrypt.checkpw(password, currentUser.get().getPassword());
    if(!checkPassword) return Optional.empty();
    return currentUser;
  }
}
