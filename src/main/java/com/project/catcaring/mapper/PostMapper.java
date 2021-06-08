package com.project.catcaring.mapper;

import com.project.catcaring.domain.Address;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostUpdateInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

  void insertPostInfo(Post newPost);
  Long findLastPostByUserId(Long userId);
  void insertLocationInfo(Address postLocation);
  void insertTagInfo(Tag postTag);

  void deletePost(Long userId, Long postId);
  boolean updatePostContent(PostUpdateInfo postContentUpdate);
  void updatePostLocation(PostUpdateInfo postLocationUpdate);

  List<PostListInfo> findAllPosts(PageRequest page);
}
