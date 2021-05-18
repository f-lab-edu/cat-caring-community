package com.project.catcaring.handler;

public class InvalidProcessException extends RuntimeException {
  public InvalidProcessException(String message) {
    super(message);
  }
}
