package com.elearning.fe.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.fe.model.CourseFeModel;
import com.elearning.fe.model.UserFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/course")
public class CourseController extends AbstractController<CourseFeModel> {

	public CourseController() {
		super(CourseFeModel.class);
	}
	
	@ModelAttribute(name = "course")
	public CourseFeModel course() {
		UserFeModel loggedInUser = authenticationFacade.getAuthenticatedUser();
		CourseFeModel course = new CourseFeModel();
		course.setProfessor(loggedInUser);
		return course;
	}
	
	@PostMapping("/add")
	public String addCourse(@Valid @ModelAttribute("course") CourseFeModel course, Errors errors) {
		return super.add(course, errors);
	}
	
	
	
	
}
