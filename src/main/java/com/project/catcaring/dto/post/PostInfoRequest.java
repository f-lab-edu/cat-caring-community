package com.project.catcaring.dto.post;

import com.project.catcaring.domain.User.Location;
import com.project.catcaring.domain.User.MemberShip;
import java.util.List;
import lombok.Getter;
import reactor.util.annotation.Nullable;

@Getter
public class PostInfoRequest {

  private String title;
  private String content;
  @Nullable
  private MemberShip postAuthorityCode;
  @Nullable
  private List<String> tagNames;
  private Location location;
  @Nullable
  private String locationDetail;
  @Nullable
  private MemberShip locationAuthorityCode;
}
