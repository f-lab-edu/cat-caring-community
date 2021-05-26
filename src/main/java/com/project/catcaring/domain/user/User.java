package com.project.catcaring.domain.user;

import com.project.catcaring.dto.user.UserChangeRequest;
import com.project.catcaring.dto.user.UserInfoRequest;
import com.project.catcaring.service.user.LoginSessionService;
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
  private static LoginSessionService loginSessionService;

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

  public static User generate(UserInfoRequest userInfoRequest) {
    return User.builder().username(userInfoRequest.getUsername())
        .password(PASSWORD_ENCODER.encode(userInfoRequest.getPassword()))
        .fullName(userInfoRequest.getFullName())
        .email(userInfoRequest.getEmail())
        .location(userInfoRequest.getLocation())
        .authorityCode(Authority.USER)
        .status(Status.MEMBER)
        .build();
  }

  public static User modify(UserChangeRequest userChangeRequest, Long userId) {
    return User.builder()
        .id(userId)
        .password(PASSWORD_ENCODER.encode(userChangeRequest.getPassword()))
        .fullName(userChangeRequest.getFullName())
        .location(userChangeRequest.getLocation()).build();
  }
}
