package com.project.catcaring.service;

import com.project.catcaring.domain.Comment;
import com.project.catcaring.error.InvalidContentIdError;
import com.project.catcaring.mapper.CommentMapper;
import com.project.catcaring.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final PostMapper postMapper;
  private final CommentMapper commentMapper;

  @Override
  @Transactional
  public void saveComment(Comment comment, Long postId) {
    boolean result = postMapper.isExistingPost(postId);
    if (! result) {
      throw new InvalidContentIdError();
    }
    commentMapper.saveComment(comment);
  }

  @Override
  @Transactional
  public void saveNestedComment(Comment comment, Long parentId) {
    boolean result = commentMapper.isExistingComment(parentId);
    if (! result) {
      throw new InvalidContentIdError();
    }
    commentMapper.saveNestedComment(comment);
  }

  @Override
  @Transactional
  public void deleteComment(Long commentId, Long userId) {
    boolean result = commentMapper.isExistingComment(commentId);
    if (! result) {
      throw new InvalidContentIdError();
    }
    commentMapper.deleteComment(commentId, userId);
    System.out.println("aaaaaa");
  }
}
