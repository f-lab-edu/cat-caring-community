package com.project.catcaring.controller;

import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostUpdateRequest;
import com.project.catcaring.service.LoginSessionService;
import com.project.catcaring.service.PostServiceImpl;
import com.project.catcaring.service.UserService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @DeleteMapping("/delete/{postId}")
  public HttpStatus deletePost(@PathVariable Long postId, HttpSession session) {
    Long userId = userService.getUserId(loginSessionService.getCurrentUsername(session));
    log.info(loginSessionService.getCurrentUsername(session) + "님이 게시물 번호 :" + postId + " 포스트를 삭제를 요청했습니다.");
    try{
      postService.deletePost(userId, postId);
      log.info("게시물 번호: " + postId + " 포스트가 삭제 되었습니다. ");
      return HttpStatus.OK;
    } catch (RuntimeException e) {
      log.warn("게시물 번호: " + postId + " 포스트 삭제 중 오류가 발생했습니다. ");
      e.printStackTrace();
      return HttpStatus.NON_AUTHORITATIVE_INFORMATION;
    }
  }

  @PatchMapping("/update")
  public HttpStatus updatePost(@RequestBody PostUpdateRequest postUpdateRequest, HttpSession session) {
    Long userId = userService.getUserId(loginSessionService.getCurrentUsername(session));
    log.info(loginSessionService.getCurrentUsername(session) + "님이 게시물 번호 :" + postUpdateRequest.getPostId() + " 포스트 수정을 요청했습니다.");

    try {
      postService.updatePost(postUpdateRequest, userId);
      log.info("게시물 번호: " + postUpdateRequest.getPostId() + " 포스트가 수정 되었습니다.");
    } catch (RuntimeException e) {
      e.printStackTrace();
      return HttpStatus.NON_AUTHORITATIVE_INFORMATION;
    }

    return HttpStatus.OK;
  }

  @GetMapping("/page/{pageId}")
  public Object getPostLists(@PathVariable("pageId") int page) {
    try {
      PageRequest pageRequest = PageRequest.builder().page(page).build();
      return postService.findPostLists(pageRequest);
    } catch (RuntimeException e) {
      return HttpStatus.NOT_FOUND;
    }
  }


}
