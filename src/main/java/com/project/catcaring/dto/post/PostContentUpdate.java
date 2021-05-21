package com.project.catcaring.dto.post;

import com.project.catcaring.domain.user.Authority;
import lombok.Builder;
import reactor.util.annotation.Nullable;

@Builder
public class PostContentUpdate {
  private final Long postId;
  private final Long userId;

  private final String content;
  @Nullable
  private final Authority postAuthorityCode;

}
