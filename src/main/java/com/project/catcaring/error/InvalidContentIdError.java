package com.project.catcaring.error;

import org.springframework.http.HttpStatus;

public class InvalidContentIdError extends BaseException {

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.NOT_FOUND;
  }

  @Override
  public String getMessage() {
    return "존재하지 않는 게시물 정보 입니다.";
  }
}
