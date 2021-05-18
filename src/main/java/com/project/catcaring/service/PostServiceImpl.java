package com.project.catcaring.service;

import com.project.catcaring.domain.Location;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Post.PostStatus;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.handler.InvalidProcessException;
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
    Post newPost = buildPostInfo(postInfoRequest, userId);
    postMapper.insertPost(newPost);
    Long postId = postMapper.findLastPostByUserId(userId);

    Location postLocation = buildLocationInfo(postInfoRequest, postId);
    postMapper.insertLocation(postLocation);

    if(postInfoRequest.getTagNames() != null) {
      for(String tagName : postInfoRequest.getTagNames()) {
        Tag postTag = Tag.builder().postId(postId).tagName(tagName).build();
        postMapper.insertTag(postTag);
      }
    }

  }

  private Post buildPostInfo(PostInfoRequest postInfoRequest, Long userId){
    Authority authority = postInfoRequest.getPostAuthorityCode();
    if(authority == null) {
      authority = Authority.ALL;
    }

    return Post.builder().userId(userId).title(postInfoRequest.getTitle())
                    .content(postInfoRequest.getContent()).authorityCode(authority)
                    .status(PostStatus.CREATED).build();
  }

  private Location buildLocationInfo(PostInfoRequest postInfoRequest, Long postId) {
    Authority authority = postInfoRequest.getLocationAuthorityCode();

    if(authority == null) {
      authority = Authority.ACTIVE_MEM;
    }

    return Location.builder().postId(postId).location(postInfoRequest.getLocation())
            .locationDetail(postInfoRequest.getLocationDetail())
            .authorityCode(authority).build();
  }

  @Override
  public List<Post> viewPost(Long postId) {
    return null;
  }

  @Override
  public void updatePost(PostInfoRequest postInfoRequest) {

  }

  @Override
  public void deletePost(Long userId, Long postId) {

    boolean result = postMapper.deletePost(userId, postId);

    if(!result) {
      throw new InvalidProcessException("해당 포스트가 존재하지 않습니다.");
    }

  }


}
