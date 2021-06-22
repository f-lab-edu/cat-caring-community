package com.project.catcaring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.catcaring.domain.User.MemberShip;
import com.project.catcaring.dto.post.PostInfoRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public class Post {

  private final Long id;
  private final Long userId;
  private final String title;
  private final String content;
  private final MemberShip authorityCode;
  private final PostStatus status;
  private final List<Tag> tagList;
  private final Address location;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime createdAt;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime modifiedAt;

  public static Post generate(PostInfoRequest postInfoRequest, Long userId) {
    MemberShip authority = postInfoRequest.getPostAuthorityCode();

    if (authority == null) {
      authority = MemberShip.DEFAULT_MEMBER;
    }
    return Post.builder()
        .userId(userId)
        .title(postInfoRequest.getTitle())
        .content(postInfoRequest.getContent())
        .authorityCode(authority)
        .status(PostStatus.CREATED)
        .build();
  }

  public enum PostStatus {
    CREATED, DELETED, MODIFIED
  }
}
