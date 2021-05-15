package com.project.catcaring.service;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User;
import com.project.catcaring.domain.user.User.Address;
import com.project.catcaring.domain.user.User.Status;
import com.project.catcaring.dto.UserChangeRequest;
import com.project.catcaring.dto.UserInfoRequest;
import com.project.catcaring.handler.DuplicateIdException;
import com.project.catcaring.mapper.UserMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
  private final UserMapper userMapper;
  private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


  public void createUser(UserInfoRequest userInfoRequest) {
    if(isUniqueId(userInfoRequest.getUsername())) {
      throw new DuplicateIdException(userInfoRequest.getUsername() + "은 이미 존재하는 아이디 입니다.");
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

  // 유저네임 unique 한지 확인
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

  public User getUserInfo(String username) {
    return userMapper.findByUsername(username);
  }

  @Transactional(rollbackFor = RuntimeException.class)
  public void deleteUser(String username) {
    boolean result = userMapper.deleteUser(username);
    if(!result) {
      log.error("error occurred while deleting user: " + username);
      throw new RuntimeException("delete ERROR! ");
    }
  }

  public void updateUserInfo(UserChangeRequest userChangeRequest, String username) {

    if(userChangeRequest.getPassword() != null) {
      userMapper.updatePassword(username, PASSWORD_ENCODER.encode(userChangeRequest.getPassword()));
      log.info(username + " 비밀번호 변경이 완료되었습니다.");
    }

    if(userChangeRequest.getFullName() != null) {
      userMapper.updateName(username, userChangeRequest.getFullName());
      log.info(username + " 이름이 변경이 완료되었습니다.");

    }

    if(userChangeRequest.getLocation() != null) {
      userMapper.updateLocation(username, userChangeRequest.getLocation());
      log.info(username + " 주소가 변경이 완료되었습니다.");

    }
  }

}
