package com.project.catcaring.controller;

import static com.project.catcaring.handler.HttpResponses.*;

import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.handler.ProcessErrorException;
import com.project.catcaring.service.user.LoginSessionService;
import com.project.catcaring.service.PostServiceImpl;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
  private final PostServiceImpl postService;
  private final LoginSessionService loginSessionService;

  @PostMapping
  public ResponseEntity<String> savePost(@RequestBody @NonNull PostInfoRequest postInfoRequest) {
    postService.uploadPost(postInfoRequest, loginSessionService.getCurrentUserId());
    return RESPONSE_OK;
  }

  @DeleteMapping("/{postId}")
  public ResponseEntity<String> deletePost(@PathVariable Long postId) {
    postService.deletePost(loginSessionService.getCurrentUserId(), postId);
    return RESPONSE_OK;
  }
}
