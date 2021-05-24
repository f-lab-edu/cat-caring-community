package com.project.catcaring.mapper;

import com.project.catcaring.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

  void saveComment(Comment comment);

}
