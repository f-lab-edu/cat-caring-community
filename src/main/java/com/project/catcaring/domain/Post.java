package com.project.catcaring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.catcaring.domain.User.MemberShip;
import com.project.catcaring.dto.post.PostInfoRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post {

  private Long id;
  private Long userId;
  private String title;
  private String content;
  private MemberShip authorityCode;
  private PostStatus status;
  private List<Tag> tagList;
  private Address location;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime modifiedAt;

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
