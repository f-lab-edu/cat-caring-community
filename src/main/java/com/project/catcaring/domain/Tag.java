package com.project.catcaring.domain;

import lombok.Builder;

@Builder
public class Tag {

  private final Long id;
  private final Long postId;
  private final String tagName;

}
