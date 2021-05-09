package com.project.catcaring.service;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User;
import com.project.catcaring.domain.user.User.Status;
import com.project.catcaring.dto.UserCreateRequest;
import com.project.catcaring.handler.DuplicateIdException;
import com.project.catcaring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserMapper userMapper;
  private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  public void createUser(UserCreateRequest userCreateRequest) {
    if(isUniqueId(userCreateRequest.getUsername())) {
      throw new DuplicateIdException(userCreateRequest.getUsername() + "은 이미 존재하는 아이디 입니다.");
    }

    String rawPassword = userCreateRequest.getPassword();


    User encryptedUserInfo = User.builder().username(userCreateRequest.getUsername())
        .password(PASSWORD_ENCODER.encode(rawPassword))
        .fullName(userCreateRequest.getFullName())
        .email(userCreateRequest.getEmail())
        .location(userCreateRequest.getLocation())
        .authorityCode(Authority.USER_1)
        .status(Status.MEMBER)
        .build();

    userMapper.insertUser(encryptedUserInfo);
  }

  // 유저네임 unique 한지 확인
  public boolean isUniqueId(String username) {

    return userMapper.isUniqueId(username);
  }

  public User login (String username, String password) {
    String rawPassword = PASSWORD_ENCODER.encode(password);
    return userMapper.findByUsernameAndPassword(username, rawPassword);
  }


}
