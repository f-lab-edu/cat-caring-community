package com.project.catcaring.service;

import com.project.catcaring.domain.Post;
import com.project.catcaring.dto.post.PostInfoRequest;
import java.util.List;

public interface PostService {

  void uploadPost(PostInfoRequest postInfoRequest, Long userId);
  List<Post> viewPost(Long postId);
  void updatePost(PostInfoRequest postInfoRequest);
  void deletePost(Long userId, Long postId);


}
