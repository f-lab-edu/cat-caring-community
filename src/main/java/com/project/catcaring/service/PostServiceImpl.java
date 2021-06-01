package com.project.catcaring.service;

import com.project.catcaring.domain.Location;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Post.PostStatus;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.handler.ProcessErrorException;
import com.project.catcaring.mapper.PostMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

  private final PostMapper postMapper;

  @Override
  public void uploadPost(PostInfoRequest postInfoRequest, Long userId) {
    postMapper.insertPost(Post.generate(postInfoRequest, userId));

    Long postId = postMapper.findLastPostByUserId(userId);

    postMapper.insertLocation(Location.generate(postInfoRequest, postId));

    if(postInfoRequest.getTagNames() != null) {
      for(String tagName : postInfoRequest.getTagNames()) {
        postMapper.insertTag(Tag.generate(tagName, postId));
      }
    }
  }

  @Override
  public void deletePost(Long userId, Long postId) {
    boolean result = postMapper.deletePost(userId, postId);
    if(!result) {
      throw new ProcessErrorException();
    }
  }
}
