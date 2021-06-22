package com.project.catcaring.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class LogoutErrorException extends HttpClientErrorException {
  public LogoutErrorException(HttpStatus statusCode) {
    super(statusCode);
  }
}
