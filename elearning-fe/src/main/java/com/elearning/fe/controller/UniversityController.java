package com.elearning.fe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elearning.fe.model.UniversityFeModel;
import com.elearning.fe.util.impl.RestCaller;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/university")
@Slf4j
public class UniversityController {

	@Autowired
	private RestCaller rest;

	@ModelAttribute(name = "university")
	public UniversityFeModel university() {
		return new UniversityFeModel();
	}

	@GetMapping("/add")
	public String addPage(Model model) {
		return "university/add-university";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("university") UniversityFeModel universityModel, Errors errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
			return "university/add-university";
		}
		
		HttpEntity<Void> response = rest.postExchange("http://localhost:8181/api/university", new HttpEntity<UniversityFeModel>(universityModel), Void.class);
		HttpHeaders header = response.getHeaders();
		System.out.println("Location: " + header.getLocation());
		return "redirect:/";

	}
	
}
