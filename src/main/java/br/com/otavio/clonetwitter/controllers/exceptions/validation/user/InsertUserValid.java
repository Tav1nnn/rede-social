package br.com.otavio.clonetwitter.controllers.exceptions.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;



import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

@Constraint(validatedBy = InsertUserValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InsertUserValid {
    String message() default "Validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
