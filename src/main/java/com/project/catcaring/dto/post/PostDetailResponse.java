package com.project.catcaring.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.catcaring.domain.Address;
import com.project.catcaring.domain.Comment;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.domain.User.MemberShip;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostDetailResponse {

  private final Long id;
  private final String title;
  private final String content;
  private final MemberShip authorityCode;
  private final List<Tag> tagList;
  private final Address location;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime createdAt;
  private final List<Comment> commentList;

  public static Optional<PostDetailResponse> generateDetail(Post postInfo) {
    Long postId = postInfo.getId();
    List<Comment> commentList = Comment.viewComments(postId);
    //List<Tag> tagList = Tag.listTags(postId);
    //Location location = Location.getInfo(postId);

    return Optional.ofNullable(PostDetailResponse.builder()
        .id(postId)
        .title(postInfo.getTitle())
        .content(postInfo.getContent())
        .authorityCode(postInfo.getAuthorityCode())
        .location(postInfo.getLocation())
        .createdAt(postInfo.getCreatedAt())
        .commentList(commentList)
        //.tagList(tagList)
        .build());
  }
}
