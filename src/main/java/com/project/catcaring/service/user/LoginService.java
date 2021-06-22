package com.project.catcaring.service.user;

/**
 *  로그인은 세션 방식 혹은 토큰 방식 여러 형태로 구현이 가능하기 때문에 LoginSerive 인터페이스를 통해
 *  로그인 방식에 대한 의존도를 낮추는 역할을 합니다.
 */

public interface LoginService {

  void loginUser(Long userId);
  void logoutUser();
  Long getCurrentUserId();
}
