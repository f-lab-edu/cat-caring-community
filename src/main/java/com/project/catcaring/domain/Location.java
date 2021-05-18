package com.project.catcaring.domain;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User.Address;
import lombok.Builder;
import lombok.Getter;

@Builder
public class Location {

  private final Long id;
  private final Long postId;
  private final Address location;
  private final String locationDetail;
  private final Authority authorityCode;

}
