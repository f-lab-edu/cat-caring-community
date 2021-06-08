package com.project.catcaring.mapper;

import com.project.catcaring.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  void insertUser(User user);
  boolean isUniqueId(String username);
  User findByUsername(@Param("username")String username);
}
