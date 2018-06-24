package com.ccs.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ccs.util.StringUtil;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {  
    
    @Override  
    public void initialize(NotEmpty constraintAnnotation) {  
    }  
  
    @Override  
    public boolean isValid(String value, ConstraintValidatorContext context) {  
        return StringUtil.isNotBlank(value);  
    }  
}  
