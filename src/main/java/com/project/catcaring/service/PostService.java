package com.project.catcaring.service;

import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostDetailResponse;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostUpdateRequest;
import java.util.List;
import java.util.Optional;

public interface PostService {

  void savePost(PostInfoRequest postInfoRequest, Long userId);
  List<PostListInfo> viewPostLists(PageRequest page);
  void updatePost(PostUpdateRequest postUpdateRequest, Long postId, Long userId);
  void deletePost(Long userId, Long postId);
  Optional<PostDetailResponse> viewPostDetail(Long postId);
}
