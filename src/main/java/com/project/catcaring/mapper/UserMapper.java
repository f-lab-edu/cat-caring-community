package com.project.catcaring.mapper;

import com.project.catcaring.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  void insertUser(User user);
  boolean isUniqueId(String username);
  User findByUsername(String username);
  User findByUserId(Long userId);
  void deleteUser(Long userId);
  void update(User user);
}
