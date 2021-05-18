package com.project.catcaring.controller;

import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.PostServiceImpl;
import com.project.catcaring.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
  private final PostServiceImpl postService;
  private final UserService userService;
  private final LoginSessionService loginSessionService;

  @PostMapping("/save")
  public HttpStatus savePost( @RequestBody @NonNull PostInfoRequest postInfoRequest, HttpSession session) {
    String username = loginSessionService.getCurrentUsername(session);
    Long userId = userService.getUserId(username);
    log.info(username + "님 (userId: " + userId + ") 의 포스트 업로드를 시작합니다. ");
    postService.uploadPost(postInfoRequest, userId);
    log.info(username + "님의 포스트 업로드가 완료되었습니다. ");
    return HttpStatus.OK;
  }


}
