package com.project.catcaring.error;

import org.springframework.http.HttpStatus;

public class UserIdMistmatchException extends BaseException {

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

  @Override
  public String getMessage() {
    return "해당 포스트에 대한 유저의 접근 권한이 없습니다.";
  }
}
