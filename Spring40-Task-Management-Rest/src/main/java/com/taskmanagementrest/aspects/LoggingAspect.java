package com.taskmanagementrest.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Configuration
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.taskmanagementrest.controller.ProjectController.*(..)) || execution(* com.taskmanagementrest.controller.TaskController.*(..))")
    private void anyControllerOperation(){}

    @Before("anyControllerOperation()")
    public void anyBeforeControllerOperationAdvice(JoinPoint joinPoint){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Before(User: {} method : {} - Parameters : {}", authentication.getName(), joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "anyControllerOperation()", returning = "results")
    public void anyAfterReturningControllerOperationAdvice(JoinPoint joinPoint, Object results){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("AfterReturning(User: {} method : {} - Results : {}", authentication.getName(), joinPoint.getSignature().toShortString(), results.toString());

    }


    @AfterThrowing(pointcut = "anyControllerOperation()", throwing = "exception")
    public void anyAfterThrowingControllerOperationAdvice(JoinPoint joinPoint, RuntimeException exception){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("After Throwing(User: {} method : {} - Exception : {}", authentication.getName(), joinPoint.getSignature().toShortString(), exception.getLocalizedMessage());

    }




}
