package com.elearning.fe.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.FacultyFeModel;
import com.elearning.fe.model.UniversityFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/faculty")
@Slf4j
public class FacultyController {

	@Autowired
	private RestOperations rest;
	
	@ModelAttribute(name = "universities")
	public Collection<UniversityFeModel> universities() throws RestClientException, URISyntaxException {
		HttpEntity<UniversityFeModel[]> universities = rest.exchange(RequestEntity.get(new URI("http://localhost:8181/api/university")).build(), UniversityFeModel[].class);
		System.out.println(Arrays.asList(universities.getBody()));
		return Arrays.asList(universities.getBody());
	}

	@ModelAttribute(name = "faculty")
	public FacultyFeModel faculty() {
		return new FacultyFeModel();
	}

	@GetMapping("/add")
	public String addPage(Model model) {
		return "faculty/add-faculty";
	}
	
	@PostMapping("/add")
	public String add(@Valid @ModelAttribute("faculty") FacultyFeModel facultyFeModel, Errors errors, @RequestHeader String host) {
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
			return "faculty/add-faculty";
		}
		
		HttpEntity<Void> response = rest.exchange("http://localhost:8181/api/faculty", HttpMethod.POST, new HttpEntity<FacultyFeModel>(facultyFeModel), Void.class);
		HttpHeaders header = response.getHeaders();
		return "redirect:/" + host + header.getLocation().getPath();

	}
}
