package com.project.catcaring.controller;

import com.project.catcaring.dto.comment.CommentInfoRequest;
import com.project.catcaring.service.CommentServiceImpl;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.UserService;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CommentController {

  private final CommentServiceImpl commentService;
  private final LoginSessionService loginSessionService;
  private final UserService userService;

  @PostMapping("posts/{postId}/reply/save")
  public HttpStatus saveComment(@RequestBody @NonNull CommentInfoRequest commentInfoRequest, @PathVariable Long postId, HttpSession session) {
    try{
      String username = loginSessionService.getCurrentUsername(session);
      Long userId = userService.getUserId(username);
      log.info(username + "님이 게시물 번호: " + postId + " 에 답변 작성을 시작했습니다. ");
      commentService.saveComment(commentInfoRequest, postId, userId);
      log.info(username + "님이 게시물 번호: " + postId + " 에 답변 작성을 완료 했습니다. ");
      return HttpStatus.OK;
    } catch (RuntimeException e){
      return HttpStatus.BAD_REQUEST;
    }
  }

}
