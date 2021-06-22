package com.project.catcaring.domain;

import com.project.catcaring.dto.user.request.UserChangeRequest;
import com.project.catcaring.dto.user.request.UserInfoRequest;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Builder
@RequiredArgsConstructor
public class User {

  private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private final Long id;
  private final String username;
  private final String password;
  private final String email;
  private final String fullName;
  private final Location location;
  private final MemberShip memberShipStatus;
  private final String accessToken;
  private final String userIntro;
  private final Status userStatus;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public static User generate(UserInfoRequest userInfoRequest) {
    return User.builder().username(userInfoRequest.getUsername())
        .password(PASSWORD_ENCODER.encode(userInfoRequest.getPassword()))
        .fullName(userInfoRequest.getFullName())
        .email(userInfoRequest.getEmail())
        .location(userInfoRequest.getLocation())
        .memberShipStatus(MemberShip.DEFAULT_MEMBER)
        .userStatus(Status.MEMBER)
        .build();
  }

  public static User modify(UserChangeRequest userChangeRequest, Long userId) {
    return User.builder()
        .id(userId)
        .password(PASSWORD_ENCODER.encode(userChangeRequest.getPassword()))
        .fullName(userChangeRequest.getFullName())
        .location(userChangeRequest.getLocation()).build();
  }

  public enum MemberShip {

    ADMIN,
    LEADER_MEM,
    BOARD_MEM,
    ACTIVE_MEM,
    SOCIETY_MEM,
    DEFAULT_MEMBER,
    ALL,
    DELETED
  }

  public enum Status {

    MEMBER, ADMIN, DELETED
  }

  public enum Location {

    DOBONG, DONGDAEMUN, DONGJAK,
    EUNPYEONG,
    GANGBUK, GANGDONG,  GANGNAM, GANGSEO, GEUMCHEON, GURO, GWANAK, GWANGJIN,
    JONGNO, JUNG, JUNGNANG,
    MAPO,
    NOWON,
    SEOCHO, SEODAEMUN, SEONGBUK, SEONGDONG, SONGPA,
    YANGCHEON, YEONGDEUNGPO, YONGSAN
  }
}
