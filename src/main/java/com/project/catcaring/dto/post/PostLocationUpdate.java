package com.project.catcaring.dto.post;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User.Address;
import lombok.Builder;
import lombok.Getter;
import reactor.util.annotation.Nullable;

@Builder
@Getter
public class PostLocationUpdate {
  private final Long postId;
  @Nullable
  private final Address location;
  @Nullable
  private final String locationDetail;
  @Nullable
  private final Authority locationAuthorityCode;


}
