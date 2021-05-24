package com.project.catcaring.mapper;

import com.project.catcaring.domain.Location;
import com.project.catcaring.domain.Post;
import com.project.catcaring.domain.Tag;
import com.project.catcaring.dto.post.PageRequest;
import com.project.catcaring.dto.post.PostContentUpdate;
import com.project.catcaring.dto.post.PostListInfo;
import com.project.catcaring.dto.post.PostLocationUpdate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper
public interface PostMapper {
  void insertPost(Post newPost);
  Long findLastPostByUserId(Long userId);
  void insertLocation(Location postLocation);
  void insertTag(Tag postTag);

  boolean deletePost(Long userId, Long postId);
  boolean updateContent(PostContentUpdate postContentUpdate);
  boolean updateLocation(PostLocationUpdate postLocationUpdate);

  List<PostListInfo> findAllPosts(PageRequest page);
  boolean existingPost(Long postId);
}
