package com.project.catcaring.domain.user;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@Builder
public class User {
  private final Long id;
  private final String username;
  private final String password;
  private final String email;
  private final String fullName;
  private final Address location;
  private final Authority authorityCode;
  private final String accessToken;
  private final String userIntro;
  private final Status status;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;


  public enum Status {
    MEMBER, DELETED
  }

  public enum Address {
    JONGNO, JUNG, YONGSAN, SEONGDONG, GWANGJIN, DONGDAEMUN,
    JUNGNANG, SEONGBUK, GANGBUK, DOBONG, NOWON, EUNPYEONG, SEODAEMUN, MAPO,
    YANGCHEON, GANGSEO, GURO, GEUMCHEON, YEONGDEUNGPO, DONGJAK,
    GWANAK, SEOCHO, GANGNAM, SONGPA, GANGDONG
  }


}
