package com.project.catcaring.controller;

import static com.project.catcaring.error.HttpResponses.RESPONSE_OK;

import com.project.catcaring.aop.annotation.CheckLogin;
import com.project.catcaring.aop.annotation.CurrentUserId;
import com.project.catcaring.domain.Comment;
import com.project.catcaring.dto.comment.CommentInfoRequest;
import com.project.catcaring.service.CommentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/{postId}")
  @CheckLogin
  public ResponseEntity<String> saveComment(@RequestBody @NonNull CommentInfoRequest commentInfoRequest, @PathVariable Long postId, @CurrentUserId Long userId) {
    commentService.saveComment(Comment.generate(commentInfoRequest, postId, 0L, userId), postId);
    return RESPONSE_OK;
  }

  @PostMapping("/{postId}/{parentId}")
  @CheckLogin
  public ResponseEntity<String> saveNestedComment(@RequestBody @NonNull CommentInfoRequest commentInfoRequest, @PathVariable Long postId, @PathVariable Long parentId, @CurrentUserId Long userId) {
    commentService.saveNestedComment(Comment.generate(commentInfoRequest, postId, parentId, userId), parentId);
    return RESPONSE_OK;
  }

  @DeleteMapping("/{commentId}")
  @CheckLogin
  public ResponseEntity<String> deleteComment(@PathVariable Long commentId, @CurrentUserId Long userId) {
    commentService.deleteComment(commentId, userId);
    return RESPONSE_OK;
  }
}
