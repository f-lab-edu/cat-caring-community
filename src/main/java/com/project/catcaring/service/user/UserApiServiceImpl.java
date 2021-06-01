package com.project.catcaring.service.user;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.dto.user.UserChangeRequest;
import com.project.catcaring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApiServiceImpl implements UserApiService{

  private final UserMapper userMapper;
  @Override
  public User getUserInfo(Long userId) {
    return userMapper.findByUserId(userId);
  }

  @Override
  public void deleteUser(Long userId) {
    userMapper.deleteUser(userId);
  }

  @Override
  public void updateUserInfo(UserChangeRequest userChangeRequest, Long userId) {
    userMapper.update(User.modify(userChangeRequest, userId));
  }
}
