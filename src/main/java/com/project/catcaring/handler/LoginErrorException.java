package com.project.catcaring.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class LoginErrorException extends HttpClientErrorException {
  public LoginErrorException(HttpStatus statusCode) {
    super(statusCode);
  }
}
