package com.project.catcaring.dto.post;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User.Address;
import lombok.Getter;
import reactor.util.annotation.Nullable;

@Getter
public class PostUpdateRequest {

  private Long postId;

  private String content;
  @Nullable
  private Authority postAuthorityCode;

  @Nullable
  private Address location; //enum
  @Nullable
  private String locationDetail;
  @Nullable
  private Authority locationAuthorityCode;

}
