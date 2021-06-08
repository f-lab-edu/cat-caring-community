package com.project.catcaring.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  loginSession 을 통해 생성자를 매번 주입하여 userId 를 찾아오게 될 경우
 *  클래스들 사이의 의존성이 너무 높아지게 됩니다.
 *  그렇기 때문에 현재 로그인 되어 있는 userId 를 불러오기 위한 체크를
 *  비즈니스 로직에서 하는것이 아닌 애노테이션을 통해 반복적인 횡단 관점으로 분리하여 관리해 줍니다.
 *
 * @Retention(RetentionPolicy.RUNTIME) : 유지 범위가 컴파일 전까지 유효하며 컴파일 이후에는 사라지게 됩니다.
 *
 * @Target(ElementType.PARAMETER) : Java compiler 에서 전달인자 선언 위치에서만 사용합니다.
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CurrentUserId {
}
