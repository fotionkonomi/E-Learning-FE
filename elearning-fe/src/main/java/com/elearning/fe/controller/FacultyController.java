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

import com.elearning.fe.model.FacultyFeModel;
import com.elearning.fe.model.UniversityFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("${requestMapping.faculty}")
@Slf4j
public class FacultyController extends AbstractController<FacultyFeModel> {

	public FacultyController() {
		super(FacultyFeModel.class);
	}
	
	@ModelAttribute(name = "universities")
	public Collection<UniversityFeModel> universities() {
		HttpEntity<UniversityFeModel[]> universities = rest.getExchange("http://localhost:8181/api/university", UniversityFeModel[].class);
		return Arrays.asList(universities.getBody());
	}

	@ModelAttribute(name = "faculty")
	public FacultyFeModel faculty() {
		return new FacultyFeModel();
	}
	
	@PostMapping("/add")
	public String addFaculty(@Valid @ModelAttribute("faculty") FacultyFeModel facultyFeModel, Errors errors) {
		return add(facultyFeModel, errors);
	}
}
