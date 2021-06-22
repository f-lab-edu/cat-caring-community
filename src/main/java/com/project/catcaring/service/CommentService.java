package com.project.catcaring.service;

import com.project.catcaring.domain.Comment;

public interface CommentService {

  void saveComment(Comment comment, Long postId);
  void saveNestedComment(Comment comment, Long parentId);
  void deleteComment(Long commentId, Long userId);
}
