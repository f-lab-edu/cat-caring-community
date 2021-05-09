package com.project.catcaring.mapper;

import com.project.catcaring.domain.user.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

  void insertUser(User user);
  boolean isUniqueId(String username);
  Optional<User> findByUsername(@Param("username")String username);

  User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
