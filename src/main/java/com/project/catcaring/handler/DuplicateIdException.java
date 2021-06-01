package com.project.catcaring.handler;

import org.springframework.http.HttpStatus;

public class DuplicateIdException extends BaseException {

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.CONFLICT;
  }

  @Override
  public String getMessage() {
    return "이미 존재하는 아이디입니다.";
  }
}
