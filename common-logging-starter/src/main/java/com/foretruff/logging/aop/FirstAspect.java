package com.foretruff.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Slf4j
public class FirstAspect {

    @Pointcut("execution(public * com.foretruff.*.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before("anyFindByIdServiceMethod() && args(id) && com.foretruff.logging.aop.CommonPointcuts.isServiceLayer() && @within(transactional)")
    public void addLogging(JoinPoint joinPoint, Object id, Transactional transactional) {
        log.info("before - invoked findById method in class {} , with id {}", joinPoint.getTarget(), id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() && com.foretruff.logging.aop.CommonPointcuts.isServiceLayer()", returning = "result")
    public void addLoggingAfterReturning(Object result) {
        log.info("after returning - invoked findById method in class {} , result {}", result.getClass(), result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() && com.foretruff.logging.aop.CommonPointcuts.isServiceLayer()", throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex) {
        log.info("after throwing - invoked findById method, exception {}: {}", ex.getClass(), ex.getMessage());
    }

    @After("anyFindByIdServiceMethod() && com.foretruff.logging.aop.CommonPointcuts.isServiceLayer()")
    public void addLoggingAfterFinally() {
        log.info("after (finally) - invoked findById method");
    }


}
