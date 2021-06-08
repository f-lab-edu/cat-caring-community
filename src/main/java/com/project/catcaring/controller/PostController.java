package com.project.catcaring.controller;

import static com.project.catcaring.error.HttpResponses.RESPONSE_OK;

import com.project.catcaring.aop.annotation.CheckLogin;
import com.project.catcaring.aop.annotation.CheckUserLoginAndPostMatch;
import com.project.catcaring.aop.annotation.CurrentUserId;
import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostUpdateRequest;
import com.project.catcaring.service.PostService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  @CheckLogin
  public ResponseEntity<String> savePost(@RequestBody @NonNull PostInfoRequest postInfoRequest, @CurrentUserId Long currentUserId) {
    postService.savePost(postInfoRequest, currentUserId);
    return RESPONSE_OK;
  }

  @DeleteMapping("/{postId}")
  @CheckUserLoginAndPostMatch
  public ResponseEntity<String> deletePost(@PathVariable Long postId, @CurrentUserId Long currentUserId) {
    postService.deletePost(postId, currentUserId);
    return RESPONSE_OK;
  }

  @PatchMapping("/{postId}")
  @CheckUserLoginAndPostMatch
  public ResponseEntity<String> updatePost(@RequestBody PostUpdateRequest postUpdateRequest, @PathVariable Long postId, @CurrentUserId Long currentUserId) {
    postService.updatePost(postUpdateRequest, postId, currentUserId);
    return RESPONSE_OK;
  }

  @GetMapping("/{pageId}")
  public Object viewPostLists(@PathVariable("pageId") int page) {
    return postService.viewPostLists(PageRequest.findScope(page));
  }
}
