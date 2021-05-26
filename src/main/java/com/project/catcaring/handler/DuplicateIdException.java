package com.project.catcaring.handler;

public class DuplicateIdException extends RuntimeException{
  public DuplicateIdException(String message) {
    super(message);
  }
}
