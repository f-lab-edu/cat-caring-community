package com.project.catcaring.domain;

import com.project.catcaring.domain.Post.PostStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import reactor.util.annotation.Nullable;

@Builder
public class Comment {
  private final Long id;
  @Nullable
  private final Long commentParent;
  private final Long postId;
  private final Long userId;
  private final String comment;
  private final LocalDateTime createdAt;
  private final PostStatus status;
}
