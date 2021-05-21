package com.project.catcaring.service;

import com.project.catcaring.domain.Post;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostUpdateRequest;
import java.util.List;

public interface PostService {

  void uploadPost(PostInfoRequest postInfoRequest, Long userId);
  List<Post> viewPost(Long postId);
  void updatePost(PostUpdateRequest postUpdateRequest, Long userId);
  void deletePost(Long userId, Long postId);


}
