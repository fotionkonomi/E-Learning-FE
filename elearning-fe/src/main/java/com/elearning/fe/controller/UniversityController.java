package com.elearning.fe.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.fe.model.UniversityFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/university")
@Slf4j
public class UniversityController extends AbstractController<UniversityFeModel> {

	public UniversityController() {
		super(UniversityFeModel.class);
	}

	@ModelAttribute(name = "university")
	public UniversityFeModel university() {
		return new UniversityFeModel();
	}

	@PostMapping("/add")
	public String addUniversity(@Valid @ModelAttribute("university") UniversityFeModel universityModel, Errors errors) {
		return super.add(universityModel, errors);
	}
	
}
