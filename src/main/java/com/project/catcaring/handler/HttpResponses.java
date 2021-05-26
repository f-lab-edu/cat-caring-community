package com.project.catcaring.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponses {
  public static final ResponseEntity<Void> RESPONSE_OK = new ResponseEntity<>(HttpStatus.OK);
  public static final ResponseEntity<Void> RESPONSE_CREATED = new ResponseEntity<>(HttpStatus.CREATED);
  public static final ResponseEntity<Void> RESPONSE_UNAUTHORIZED = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  public static final ResponseEntity<Void> RESPONSE_NOT_FOUND = new ResponseEntity<>(HttpStatus.NOT_FOUND);
}
