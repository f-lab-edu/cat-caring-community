package com.project.catcaring.domain;

import com.project.catcaring.domain.user.Authority;
import com.project.catcaring.domain.user.User.Address;
import com.project.catcaring.dto.post.PostInfoRequest;
import lombok.Builder;

@Builder
public class Location {
  private final Long id;
  private final Long postId;
  private final Address location;
  private final String locationDetail;
  private final Authority authorityCode;

  public static Location generate(PostInfoRequest postInfoRequest, Long postId) {
    Authority authority = postInfoRequest.getLocationAuthorityCode();
    if(authority == null) {
      authority = Authority.ACTIVE_MEM;
    }
    return Location.builder().postId(postId).location(postInfoRequest.getLocation())
        .locationDetail(postInfoRequest.getLocationDetail())
        .authorityCode(authority).build();
  }
}
