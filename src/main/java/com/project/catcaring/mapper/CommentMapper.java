package com.project.catcaring.mapper;

import com.project.catcaring.domain.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

  void saveComment(Comment comment);
  boolean isExistingComment(Long commentId);
  void saveNestedComment(Comment comment);
  void deleteComment(Long commentId, Long userId);
  List<Comment> viewComments(Long postId);
}