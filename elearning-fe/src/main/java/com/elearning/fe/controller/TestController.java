package com.elearning.fe.controller;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.fe.model.CourseFeModel;
import com.elearning.fe.model.TestFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController extends AbstractController<TestFeModel> {

	public TestController() {
		super(TestFeModel.class);
	}

	@ModelAttribute(name = "test")
	public TestFeModel test() {
		return new TestFeModel();
	}

	@ModelAttribute(name = "courses")
	public Collection<CourseFeModel> courses() {
		HttpEntity<CourseFeModel[]> courses = rest.postExchange("http://localhost:8181/api/course/user",
				new HttpEntity<Long>(authenticationFacade.getAuthenticatedUser().getId()), CourseFeModel[].class);
		return Arrays.asList(courses.getBody());
	}

	@PostMapping("/add")
	public String addTest(@Valid @ModelAttribute("test") TestFeModel testFeModel, Errors errors) {
		return add(testFeModel, errors);
	}

}
