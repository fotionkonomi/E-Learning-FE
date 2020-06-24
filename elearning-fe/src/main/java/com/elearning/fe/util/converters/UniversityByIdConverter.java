package com.elearning.fe.util.converters;

import org.springframework.stereotype.Component;

import com.elearning.fe.model.UniversityFeModel;

@Component
public class UniversityByIdConverter extends ByIdConverter<UniversityFeModel> {
	
	public UniversityByIdConverter() {
		super(UniversityFeModel.class);
	}

}
