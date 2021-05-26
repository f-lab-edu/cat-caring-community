package com.project.catcaring.config.auth;

import com.project.catcaring.domain.user.User;
import com.project.catcaring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
  private final UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User userInfo = userMapper.findByUsername(username);
    if(userInfo == null) {
      throw new UsernameNotFoundException(username);
    }
    System.out.println(username);

    return new UserDetail(userInfo);
  }
}
