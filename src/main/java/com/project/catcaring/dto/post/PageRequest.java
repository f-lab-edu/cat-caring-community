package com.project.catcaring.dto.post;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageRequest {

  private final int page;
  private final int size = 10;
  private final int offset;

  private int getOffset() {
    return size * page - size;
  }

}
