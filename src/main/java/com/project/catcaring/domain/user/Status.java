package com.project.catcaring.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
  CREATED("STATUS_CREATED"),
  MODIFIED("STATUS_MODIFIED"),
  DELETED("STATUS_DELETED");
  private final String key;
}
