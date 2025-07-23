package org.java.financespring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(org.java.financespring.annotations.SensitiveLog) || " +
            "@within(org.java.financespring.annotations.SensitiveLog)")
    public Object logSensitiveMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String transactionId = UUID.randomUUID().toString();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        String userId = getCurrentUserId();

        MDC.put("transactionId", transactionId);
        MDC.put("class", className);
        MDC.put("method", methodName);
        MDC.put("userId", userId);

        logger.info("Start method execution");

        long startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            MDC.put("durationMs", String.valueOf(duration));

            logger.info("Method executed successfully | Result: {} | Duration: {} ms", result, duration);

            return result;

        } catch (Throwable e) {
            logger.error("Exception during method execution | Message: {}", e.getMessage(), e);
            throw e;
        } finally {
            MDC.clear();
        }
    }

    private String getCurrentUserId() {
        return "anonymous";
    }
}
