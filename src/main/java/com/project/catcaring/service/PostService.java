package com.project.catcaring.service;

import com.project.catcaring.domain.Post;
import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostUpdateRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

  void uploadPost(PostInfoRequest postInfoRequest, Long userId);
  List<PostListInfo> findPostLists(PageRequest page);
  void updatePost(PostUpdateRequest postUpdateRequest, Long userId);
  void deletePost(Long userId, Long postId);


}
