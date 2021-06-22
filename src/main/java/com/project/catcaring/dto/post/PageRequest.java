package com.project.catcaring.dto.post;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageRequest {

  private final int page;
  private final int size;
  private final int offset;

  private int getOffset() {
    return size * page - size;
  }

  public static PageRequest findScope(int page) {
    return PageRequest.builder()
        .page(page)
        .size(10)
        .build();
  }
}
