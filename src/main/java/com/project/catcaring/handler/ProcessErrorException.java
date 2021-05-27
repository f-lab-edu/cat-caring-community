package com.project.catcaring.handler;

import org.springframework.http.HttpStatus;

public class ProcessErrorException extends BaseException {

  @Override
  public HttpStatus getHttpStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

  @Override
  public String getMessage() {
    return "실행 중 오류가 발생하였습니다.";
  }
}
