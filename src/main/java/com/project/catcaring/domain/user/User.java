package com.project.catcaring.domain.user;

import java.time.LocalDateTime;

public class User {
  private Long id;
  private String username;
  private String password;
  private String email;
  private String fullName;
  private LocationAdd location;
  private Authority authorityCode;
  private String accessToken;
  private String userIntro;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Status status;

}
