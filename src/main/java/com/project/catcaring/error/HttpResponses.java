package com.project.catcaring.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponses {

  public static final ResponseEntity<String> RESPONSE_OK = new ResponseEntity<>("TASK COMPLETED", HttpStatus.OK);
  public static final ResponseEntity<String> RESPONSE_CREATED = new ResponseEntity<>("TASK CREATED", HttpStatus.CREATED);
  public static final ResponseEntity<String> RESPONSE_BAD_REQUEST = new ResponseEntity<>("PROCESS ERROR", HttpStatus.BAD_REQUEST);
}
