package com.project.catcaring.service;

import com.project.catcaring.domain.Address;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostInfoRequest;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostUpdateInfo;
import com.project.catcaring.dto.post.PostUpdateRequest;
import com.project.catcaring.error.UserIdMistmatchException;
import com.project.catcaring.mapper.PostMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

  private final PostMapper postMapper;

  @Override
  @Transactional
  public void savePost(PostInfoRequest postInfoRequest, Long userId) {
    postMapper.insertPostInfo(Post.generate(postInfoRequest, userId));
    Long postId = postMapper.findLastPostByUserId(userId);

    postMapper.insertLocationInfo(Address.generate(postInfoRequest, postId));


    if (postInfoRequest.getTagNames() != null) {
      for (String tagName : postInfoRequest.getTagNames()) {
        postMapper.insertTagInfo(Tag.generate(tagName, postId));
      }
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<PostListInfo> viewPostLists(PageRequest page) {
    return postMapper.findAllPosts(page);
  }

  @Override
  @Transactional
  public void updatePost(PostUpdateRequest postUpdateRequest, Long postId, Long userId) {
    boolean modified = postMapper.updatePostContent(PostUpdateInfo.updatePostContent(postUpdateRequest, postId, userId));
    if (modified) {
      postMapper
          .updatePostLocation(PostUpdateInfo.updatePostLocation(postUpdateRequest, postId, userId));
    } else {
      throw new UserIdMistmatchException();
    }
  }

  @Override
  @Transactional
  public void deletePost(Long userId, Long postId) {
    postMapper.deletePost(userId, postId);
  }
}
