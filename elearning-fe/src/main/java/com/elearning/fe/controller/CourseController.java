package com.elearning.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.fe.model.CourseFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/course")
public class CourseController extends AbstractController<CourseFeModel> {

	public CourseController() {
		super(CourseFeModel.class);
	}
	
	
	
	
}
