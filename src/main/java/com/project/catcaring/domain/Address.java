package com.project.catcaring.domain;

import com.project.catcaring.domain.User.Location;
import com.project.catcaring.domain.User.MemberShip;
import com.project.catcaring.dto.post.PostInfoRequest;
import lombok.Builder;

@Builder
public class Address {

  private final Long id;
  private final Long postId;
  private final Location location;
  private final String locationDetail;
  private final MemberShip authorityCode;

  public static Address generate(PostInfoRequest postInfoRequest, Long postId) {
    MemberShip authority = postInfoRequest.getLocationAuthorityCode();

    if(authority == null) {
      authority = MemberShip.ACTIVE_MEM;
    }

    return Address.builder()
        .postId(postId)
        .location(postInfoRequest.getLocation())
        .locationDetail(postInfoRequest.getLocationDetail())
        .authorityCode(authority).build();
  }
}
