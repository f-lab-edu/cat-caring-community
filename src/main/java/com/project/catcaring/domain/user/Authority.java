package com.project.catcaring.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum Authority implements GrantedAuthority {
  ADMIN("AUTHORITY_ADMIN"),
  USER_1("AUTHORITY_USER1"),
  USER_2("AUTHORITY_USER2"),
  USER_3("AUTHORITY_USER3"),
  USER_4("AUTHORITY_USER4"),
  USER_5("AUTHORITY_USER5");

  private final String key;

  @Override
  public String getAuthority() {
    return null;
  }
}
