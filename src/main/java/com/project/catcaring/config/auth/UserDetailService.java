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
    User userInfo = userMapper.findByUsername(username).orElseThrow(() -> {
      return new UsernameNotFoundException(username +" 사용자를 찾을 수 없습니다.");
    });
    return (UserDetails) userInfo;
  }
}
