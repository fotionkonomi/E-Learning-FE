package com.elearning.fe.util.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.elearning.fe.model.BaseClassModel;
import com.elearning.fe.util.constraints.annotation.NotUnselected;

@Component
public class SelectValidator implements ConstraintValidator<NotUnselected, BaseClassModel> {

	@Override
	public boolean isValid(BaseClassModel value, ConstraintValidatorContext context) {
		if(value.getId() != null) {
			return true;
		}
		
		return false;
	}
	

}
