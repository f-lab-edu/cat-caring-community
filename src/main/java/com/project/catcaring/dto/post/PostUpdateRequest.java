package com.project.catcaring.dto.post;

import com.project.catcaring.domain.User;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class PostUpdateRequest {

  private String content;
  @Nullable
  private User.MemberShip postAuthorityCode;
  @Nullable
  private User.Location location;
  @Nullable
  private String locationDetail;
  @Nullable
  private User.MemberShip locationAuthorityCode;
}
