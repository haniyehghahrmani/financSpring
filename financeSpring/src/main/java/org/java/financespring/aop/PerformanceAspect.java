package org.java.financespring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class PerformanceAspect {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceAspect.class);

    @Pointcut("execution(* org.java.financespring.service..*(..))")
    public void servicePointcut() {
    }

    @Pointcut("execution(* org.java.financespring.controller..*(..))")
    public void controllerPointcut() {
    }

    @Around("servicePointcut() || controllerPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();

        logger.info("Start Method: {}, Args: {}", method, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed(args);
            logger.info("End Method: {}, Return: {}, Execution Time: {} ms", method, result, System.currentTimeMillis() - startTime);
            return result;
        } catch (Throwable e) {
            return e;
        }
    }
}
