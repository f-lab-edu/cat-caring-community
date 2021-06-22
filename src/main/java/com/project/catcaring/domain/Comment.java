package com.project.catcaring.domain;

import com.project.catcaring.domain.Post.PostStatus;
import com.project.catcaring.dto.comment.CommentInfoRequest;
import com.project.catcaring.mapper.CommentMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public class Comment {

  private static CommentMapper commentMapper;

  private final Long id;
  @Nullable
  private final Long commentParentId;
  private final Long postId;
  private final Long userId;
  private final String comment;
  private final LocalDateTime createdAt;
  private final PostStatus status;

  public static Comment generate(CommentInfoRequest commentInfoRequest, Long postId, Long commentParentId, Long userId) {
    return Comment.builder()
        .postId(postId)
        .commentParentId(commentParentId)
        .userId(userId)
        .comment(commentInfoRequest.getComment())
        .status(PostStatus.CREATED).build();
  }

  public static List<Comment> viewComments(Long postId) {
    return commentMapper.viewComments(postId);
  }
}
