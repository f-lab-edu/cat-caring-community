package com.project.catcaring.service;

import com.project.catcaring.dto.comment.CommentInfoRequest;

public interface CommentService {

  void saveComment(CommentInfoRequest commentInfoRequest, Long postId, Long userId);


}
