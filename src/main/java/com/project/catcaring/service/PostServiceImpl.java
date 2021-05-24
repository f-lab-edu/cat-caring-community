package com.project.catcaring.service;

import com.project.catcaring.domain.Location;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Post.PostStatus;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostContentUpdate;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostLocationUpdate;
import com.project.catcaring.dto.post.PostUpdateRequest;
import com.project.catcaring.handler.InvalidProcessException;
import com.project.catcaring.mapper.PostMapper;
import java.lang.reflect.Field;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Log4j2
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
  @Transactional(readOnly = true)
  public List<PostListInfo> findPostLists(PageRequest page) {
    return postMapper.findAllPosts(page);
  }

  @Override
  public void updatePost(PostUpdateRequest postUpdateRequest, Long userId) {

    PostContentUpdate updatePost = PostContentUpdate.builder().postId(postUpdateRequest.getPostId())
                                  .userId(userId).content(postUpdateRequest.getContent())
                                  .postAuthorityCode(postUpdateRequest.getPostAuthorityCode())
                                  .build();

    boolean result = postMapper.updateContent(updatePost);

    PostLocationUpdate updateLocation = PostLocationUpdate.builder().postId(postUpdateRequest.getPostId())
                                        .location(postUpdateRequest.getLocation())
                                        .locationDetail(postUpdateRequest.getLocationDetail())
                                        .locationAuthorityCode(postUpdateRequest.getLocationAuthorityCode()).build();

    boolean nullChecker = allEmptyFields(updateLocation);
    log.info("location null checker:" + nullChecker);

    if(result && !nullChecker) {
      boolean locationResult = postMapper.updateLocation(updateLocation);
      if(!locationResult) {
        throw new InvalidProcessException("포스트 location 정보 수정 중 오류가 발생 했습니다.");
      }
    } else if (!result) {
      throw new InvalidProcessException("해당 포스트 수정 중 오류가 발생 했습니다.");
    }
  }

  private boolean allEmptyFields(PostLocationUpdate postLocationUpdate) {
    if(postLocationUpdate.getLocation() == null && postLocationUpdate.getLocationDetail() == null && postLocationUpdate.getLocationAuthorityCode() == null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void deletePost(Long userId, Long postId) {

    boolean result = postMapper.deletePost(userId, postId);

    if(!result) {
      throw new InvalidProcessException("해당 포스트가 존재하지 않습니다.");
    }

  }



}
