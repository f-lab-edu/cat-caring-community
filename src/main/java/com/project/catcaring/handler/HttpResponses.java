package com.project.catcaring.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponses {
  public static final ResponseEntity<String> RESPONSE_OK = new ResponseEntity<>("TASK COMPLETED", HttpStatus.OK);
  public static final ResponseEntity<String> RESPONSE_CREATED = new ResponseEntity<>("CREATED INTO DATABASE", HttpStatus.CREATED);
  public static final ResponseEntity<String> RESPONSE_UNAUTHORIZED = new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
  public static final ResponseEntity<String> RESPONSE_NOT_FOUND = new ResponseEntity<>("ERROR OCCURRED DURING PROCESS", HttpStatus.NOT_FOUND);
}
