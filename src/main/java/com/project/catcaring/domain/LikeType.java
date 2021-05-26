package com.project.catcaring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LikeType {
  POST("LIKETYPE_POST"),
  COMMENT("LIKETYPE_COMMENT");

  private final String key;

}
