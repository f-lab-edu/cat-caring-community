package com.project.catcaring.mapper;

import com.project.catcaring.domain.Location;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.dto.post.PostContentUpdate;
import com.project.catcaring.dto.post.PostLocationUpdate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
  void insertPost(Post newPost);
  Long findLastPostByUserId(Long userId);
  void insertLocation(Location postLocation);
  void insertTag(Tag postTag);

  boolean deletePost(Long userId, Long postId);
  boolean updateContent(PostContentUpdate postContentUpdate);
  boolean updateLocation(PostLocationUpdate postLocationUpdate);
}