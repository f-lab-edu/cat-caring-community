package com.project.catcaring.error;

import org.springframework.http.HttpStatus;

abstract class BaseException extends RuntimeException {

  public abstract HttpStatus getHttpStatus();
  public abstract String getMessage();

}
