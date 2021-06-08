package com.project.catcaring.service.user;

import com.project.catcaring.domain.User;
import com.project.catcaring.dto.user.request.UserChangeRequest;
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
  public void updateUser(UserChangeRequest userChangeRequest, Long userId) {
    userMapper.updateUser(User.modify(userChangeRequest, userId));
  }
}
