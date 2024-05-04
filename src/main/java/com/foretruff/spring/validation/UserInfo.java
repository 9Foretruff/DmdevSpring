package com.foretruff.spring.validation;

import com.foretruff.spring.validation.imp.UserInfoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInfoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserInfo {
    String message() default "First name or lastname should be filled in";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
