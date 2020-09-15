package com.elearning.fe.util.converters;

import org.springframework.stereotype.Component;

import com.elearning.fe.model.CourseFeModel;

@Component
public class CourseByIdConverter extends ByIdConverter<CourseFeModel> {

	public CourseByIdConverter() {
		super(CourseFeModel.class);
	}
}
