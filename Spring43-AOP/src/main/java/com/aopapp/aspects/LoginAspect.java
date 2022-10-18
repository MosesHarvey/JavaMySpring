package com.aopapp.aspects;


import com.aopapp.controller.ProductController;
import com.aopapp.entity.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class LoginAspect {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

//    @Pointcut("execution(* com.Aop.controller.ProductController.*(..))")
//    public void pointcut(){
//
//    }
//
//    @Before("pointcut()")
//    public void log(){
//        logger.info("-------------------");
//    }
//
//    @Before("execution(* com.Aop.controller.ProductController.*(..))")
//    public void beforeAdvice(){
//        logger.info("-------------------");
//    }


    //execution

    @Pointcut("execution(* com.aopapp.controller.ProductController.up*(..))")
    private void anyUpdateOperation(){}

    @Pointcut("execution(* com.aopapp.repository.ProductRepository.findById(Long))")
    private void anyProductRepositoryFindById(){}

    @Before("anyProductRepositoryFindById()")
    public void beforeProductRepoAdvice(JoinPoint joinPoint){
        logger.info("Before -> Method {} - Arguments : {} - Target: {}", joinPoint, joinPoint.getArgs(),joinPoint.getTarget() );
    }

    @Before("anyUpdateOperation()")
    public void beforeControllerAdvice(JoinPoint joinPoint){
        logger.info("Before -> Method {} - Arguments : {} - Target: {}", joinPoint, joinPoint.getArgs(),joinPoint.getTarget() );
    }

    // within
    @Pointcut("within(com.aopapp.controller..*)")
    private void anyControllerOperation(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void anyServiceAnnotatedOperation(){}

    @Before("anyServiceAnnotatedOperation() || anyControllerOperation()")
    public void beforeControllerAdvice2(JoinPoint joinPoint){
        logger.info("Before -> Method {} - Arguments : {} - Target: {}", joinPoint, joinPoint.getArgs(),joinPoint.getTarget() );

    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void anyDeleteProductOperation(){}

    @Before("anyDeleteProductOperation()")
    public void beforeControllerAdvice3(JoinPoint joinPoint){
        logger.info("Before -> Method {} - Arguments : {} - Target: {}", joinPoint, joinPoint.getArgs(),joinPoint.getTarget() );
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void antGetProductOperation(){}

    @AfterReturning(pointcut = "antGetProductOperation()", returning = "results")
    public void afterReturningControllerAdvice(JoinPoint joinPoint, Product results){
        logger.info("After Returning(Mono Result) -> Method {} - results : {} ", joinPoint.getSignature().toString(), results );

    }

    @AfterReturning(pointcut = "antGetProductOperation()", returning = "results")
    public void afterReturningControllerAdvice(JoinPoint joinPoint, List<Product> results){
        logger.info("After Returning(List Result) -> Method {} - results : {} ", joinPoint.getSignature().toString(), results );

    }

}
