package com.elearning.fe.util.constraints.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.elearning.fe.util.constraints.SelectValidator;

@Documented
@Constraint(validatedBy = SelectValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotUnselected {
	
	String message() default "{custom.validation.notUnselect}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
