package com.foretruff.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
public class SecondAspect {
    @Around("com.foretruff.logging.aop.FirstAspect.anyFindByIdServiceMethod() && com.foretruff.logging.aop.CommonPointcuts.isServiceLayer()")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("AROUND - invoked findById method in class {}", joinPoint.getTarget());
        try {
            Object result = joinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {} , result {}", joinPoint.getTarget(), result);
            return result;
        } catch (Throwable ex) {
            log.info("AROUND after throwing - invoked findById method, exception {}: {}", ex.getClass(), ex.getMessage());
            throw ex;
        } finally {
            log.info("AROUND after (finally) - invoked findById method");
        }
    }
}
