package com.project.catcaring.service;

import com.project.catcaring.domain.Comment;
import com.project.catcaring.domain.Post.PostStatus;
import com.project.catcaring.dto.comment.CommentInfoRequest;
import com.project.catcaring.handler.InvalidProcessException;
import com.project.catcaring.mapper.CommentMapper;
import com.project.catcaring.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

  private final CommentMapper commentMapper;
  private final PostMapper postMapper;

  @Override
  @Transactional
  public void saveComment(CommentInfoRequest commentInfoRequest, Long postId, Long userId) {
    boolean result = postMapper.existingPost(postId);
    if(!result) {
      log.error("게시물: " + postId + "이 존재하지 않는 게시물 입니다. ");
      throw new InvalidProcessException("invalid postId");
    }

    Comment comment = Comment.builder().postId(postId).userId(userId)
                     .comment(commentInfoRequest.getComment()).status(PostStatus.CREATED).build();

    commentMapper.saveComment(comment);

  }
}
