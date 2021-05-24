package com.project.catcaring.domain;

import com.project.catcaring.domain.Post.PostStatus;
import java.time.LocalDateTime;

public class Comment {
  private Long id;
  private Long parent_id;
  private Long post_id;
  private Long user_id;
  private String comment;
  private LocalDateTime createdAt;
  private PostStatus status;

}