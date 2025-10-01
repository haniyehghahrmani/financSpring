package org.java.financespring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* org.java.financespring.service..*(..))")
    public void servicePointcut() {
    }

    @Pointcut("execution(* org.java.financespring.controller..*(..))")
    public void controllerPointcut() {
    }

    @Before("servicePointcut() || controllerPointcut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("Before method: {}", method);
    }

    @After("servicePointcut() || controllerPointcut()")
    public void afterMethod(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("After method: {}", method);
    }

    @AfterThrowing(value = "servicePointcut() || controllerPointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        String method = joinPoint.getSignature().toShortString();
        logger.error("Exception in method: {} with message: {}", method, ex.getMessage());
    }

    @AfterReturning(value = "servicePointcut()||controllerPointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("After returning from method: {} with result: {}", method, result);
    }

}
