package com.foretruff.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
@Order(2)
public class SecondAspect {
    @Around("com.foretruff.spring.aop.FirstAspect.anyFindByIdServiceMethod() && target(service) && args(id)")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("AROUND - invoked findById method in class {} , with id {}", service, id);
        try {
            var result = joinPoint.proceed();
            log.info("AROUND after returning - invoked findById method in class {} , result {}", service, result);
            return result;
        }catch (Throwable ex){
            log.info("AROUND after throwing - invoked findById method in class {} , exception {}: {}", service, ex.getClass(), ex.getMessage());
            throw ex;
        }finally {
            log.info("AROUND after (finnaly) - invoked findById method in class {}", service);
        }
    }
}
