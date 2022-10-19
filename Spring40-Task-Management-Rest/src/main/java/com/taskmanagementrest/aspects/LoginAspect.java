package com.taskmanagementrest.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Configuration
public class LoginAspect {

    Logger log = LoggerFactory.getLogger(LoginAspect.class);

    @Pointcut("execution(* com.taskmanagementrest.controller.ProjectContoller.*(..)) || execution(* com.taskmanagementrest.controller.TaskController.*(..))")
    private void anyControllerOperation(){}

    @Before("anyControllerOperation()")
    public void anyBeforeControllerOperationAdvice(JoinPoint joinPoint){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Before(User: {} method : {} - Parameters : {}", authentication.getName(), joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }


}
