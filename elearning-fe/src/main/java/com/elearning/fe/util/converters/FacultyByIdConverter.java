package com.elearning.fe.util.converters;

import org.springframework.stereotype.Component;

import com.elearning.fe.model.FacultyFeModel;

@Component
public class FacultyByIdConverter extends ByIdConverter<FacultyFeModel> {

	public FacultyByIdConverter() {
		super(FacultyFeModel.class);
	}
}
