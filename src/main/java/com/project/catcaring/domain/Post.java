package com.project.catcaring.domain;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.Status;
import java.time.LocalDateTime;

public class Post {
  private Long id;
  private Long userId;
  private String title;
  private String content;
  private Authority authorityCode;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Status status;

}
