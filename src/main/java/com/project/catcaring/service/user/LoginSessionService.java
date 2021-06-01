package com.project.catcaring.service.user;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LoginSessionService implements LoginService{

  private final HttpSession session;
  private static final String USER_ID = "USER_ID";

  @Override
  public void loginUser(Long userId) {
    session.setAttribute(USER_ID, userId);
  }

  @Override
  public void logoutUser() {
    session.removeAttribute(USER_ID);
  }

  @Override
  public Long getCurrentUserId() {
    return (Long)session.getAttribute(USER_ID);
  }
}
