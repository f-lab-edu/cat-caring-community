package com.project.catcaring.aop.aspect;

import com.project.catcaring.handler.LoginErrorException;
import com.project.catcaring.handler.ProcessErrorException;
import com.project.catcaring.service.user.LoginSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class CheckUserProcessAspect {

  private final LoginSessionService loginSessionService;

  @Around("@annotation(com.project.catcaring.aop.annotation.CheckUserProcess)")
  public Object checkLoginAndProcessResult(ProceedingJoinPoint joinPoint) throws HttpClientErrorException {
    Long currentUserId = loginSessionService.getCurrentUserId();
    if(currentUserId == null) {
      log.info(" ===== 회원이 로그인이 필요합니다. ===== ");
      throw new LoginErrorException(HttpStatus.NOT_FOUND);
    }
    log.info(" ===== 회원이 로그인 되어 있는 상태입니다 ===== ");
    log.info(" ===== " + joinPoint.getSignature() + " 작업을 시작합니다. ===== ");
    try {
      Object result = joinPoint.proceed();
      log.info(" ===== 작업이 성공적으로 업데이트 되었습니다. ===== ");
      return result;
    } catch (Throwable throwable) {
      log.info(" ===== 작업 중 오류가 발생했습니다. ===== ");
      throw new ProcessErrorException();
    }
  }
}
