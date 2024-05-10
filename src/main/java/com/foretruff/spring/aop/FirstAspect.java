package com.foretruff.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Slf4j
@Component
public class FirstAspect {

    /*
        @within -check annotation on the class level

     */

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    //within - check class type name
    @Pointcut("within(com.foretruff.spring.service.*Service)")
    public void isServiceLayer() {
    }

    // this - check aop proxy class type
    // target - check target object class type

    @Pointcut("this(org.springframework.data.repository.Repository)")
//    @Pointcut("target(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }

    /*
    @annotation - check annotation on method level
     */
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    /*
    args - check method param type
    * - any param type
    .. - 0+ any params type
     */

    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {
    }

      /*
    args - check annotation on the param type
    * - any param type
    .. - 0+ any params type
     */

    @Pointcut("isControllerLayer() && @args(com.foretruff.spring.validation.UserInfo,..)")
    public void hasUserInfoParamAnnotation() {
    }

    /*
           bean - check bean name
     */

    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }


    @Pointcut("execution(public * com.foretruff.spring.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod() " +
                    "&& args(id) " +
                    "&& target(service) " +
                    "&& this(serviceProxy)" +
                    "&& @within(transactional)",
            argNames = "joinPoint,id,service,serviceProxy,transactional")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy,
                           Transactional transactional) {
        log.info("before - invoked findById method in class {} , with id {}", service, id);
    }

    @AfterReturning(value = "anyFindByIdServiceMethod()" +
                            "&& target(service)",
            returning = "result")
    public void addLoggingAfterReturning(Object result,
                                         Object service) {
        log.info("after returning - invoked findById method in class {} , result {}", service, result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod()" +
                           "&& target(service)",
            throwing = "ex")
    public void addLoggingAfterThrowing(Throwable ex,
                                  Object service) {
        log.info("after throwing - invoked findById method in class {} , exception {}: {}", service, ex.getClass(), ex.getMessage());
    }

    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("after (finnaly) - invoked findById method in class {}", service);
    }


}
