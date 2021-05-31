package com.project.catcaring.service;

import com.project.catcaring.dto.post.PostInfoRequest;

public interface PostService {
  void uploadPost(PostInfoRequest postInfoRequest, Long userId);
}
