package com.elearning.fe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.UniversityFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/university")
@Slf4j
public class UniversityController {

	@Autowired
	private RestOperations rest;

	@ModelAttribute(name = "university")
	public UniversityFeModel university() {
		return new UniversityFeModel();
	}

	@GetMapping("/add")
	public String addPage(Model model) {
		return "add-university";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("university") UniversityFeModel universityModel, Errors errors) {
		if (errors.hasErrors()) {
			
			errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
		}

		String string = rest.postForObject("http://localhost:8181/api/university/add", universityModel, String.class);
		System.out.println(string);
		return "redirect:/";

	}
}
