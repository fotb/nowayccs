package com.ccs.web.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented  
@Target({ElementType.FIELD, ElementType.PARAMETER})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy = NotEmptyValidator.class)  
public @interface NotEmpty {  
  
    String message() default "not_empty";  
  
    Class<?>[] groups() default {};  
  
    Class<? extends Payload>[] payload() default {};  
} 