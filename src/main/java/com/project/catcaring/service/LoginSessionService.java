package com.project.catcaring.service;

import com.project.catcaring.domain.user.User;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LoginSessionService implements LoginService{
  private static final String USER_ID = "USER_ID";

  @Override
  public void loginUser(HttpSession session, String username) {
    session.setAttribute(USER_ID, username);
  }

  @Override
  public void logoutUser(HttpSession session) {
    session.removeAttribute(USER_ID);
  }

  @Override
  public String getCurrentUsername(HttpSession session) {
    return (String)session.getAttribute(USER_ID);
  }
}
