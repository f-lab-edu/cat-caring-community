package com.project.catcaring.dto.post;

import com.project.catcaring.domain.User;
import com.project.catcaring.domain.User.MemberShip;
import lombok.Builder;
import reactor.util.annotation.Nullable;

@Builder
public class PostUpdateInfo {

  private final Long postId;
  private final Long userId;
  private final String content;
  @Nullable
  private final MemberShip postAuthorityCode;
  @Nullable
  private User.Location location;
  @Nullable
  private String locationDetail;
  @Nullable
  private MemberShip locationAuthorityCode;

  public static PostUpdateInfo updatePostContent(PostUpdateRequest postUpdateRequest, Long postId, Long userId) {
    return PostUpdateInfo.builder()
        .postId(postId)
        .userId(userId)
        .content(postUpdateRequest.getContent())
        .postAuthorityCode(postUpdateRequest.getPostAuthorityCode())
        .build();
  }

  public static PostUpdateInfo updatePostLocation(PostUpdateRequest postUpdateRequest, Long postId, Long userId) {
    return PostUpdateInfo.builder()
        .postId(postId)
        .location(postUpdateRequest.getLocation())
        .locationDetail(postUpdateRequest.getLocationDetail())
        .locationAuthorityCode(postUpdateRequest.getLocationAuthorityCode())
        .build();
  }
}
