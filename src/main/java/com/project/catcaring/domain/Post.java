package com.project.catcaring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.catcaring.domain.user.Authority;
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
  private final Authority authorityCode;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime createdAt;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime modifiedAt;
  private final PostStatus status;
  private final List<Tag> tagList;
  private final Location location;

  public enum PostStatus {
    CREATED, DELETED, MODIFIED
  }

  public static Post generate(PostInfoRequest postInfoRequest, Long userId) {
    Authority authority = postInfoRequest.getPostAuthorityCode();
    if(authority == null) {
      authority = Authority.ALL;
    }

    return Post.builder().userId(userId).title(postInfoRequest.getTitle())
        .content(postInfoRequest.getContent()).authorityCode(authority)
        .status(PostStatus.CREATED).build();
  }

}
