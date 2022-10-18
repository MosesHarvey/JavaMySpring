package com.aopapp.aspects;


import com.aopapp.contoller.ProductController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

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

    @Pointcut("execution(* com.aopapp.contoller.ProductController.up*(..))")
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

}
