package com.elearning.fe.controller.rest;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.fe.model.FacultyFeModel;
import com.elearning.fe.util.impl.RestCaller;

@RestController
@RequestMapping("/faculty")
public class FacultyAjaxController {

	@Autowired
	private RestCaller rest;
	
	@GetMapping("/university/{universityId}")
	public List<FacultyFeModel> facultiesOfAUniversity(@PathVariable("universityId") Long universityId) throws URISyntaxException {
		HttpEntity<FacultyFeModel[]> faculties = rest.getExchange("http://localhost:8181/api/faculty/university/" + universityId, FacultyFeModel[].class);
		return Arrays.asList(faculties.getBody());
	}
	
	@GetMapping
	public List<FacultyFeModel> faculties() throws URISyntaxException {
		HttpEntity<FacultyFeModel[]> faculties = rest.getExchange("http://localhost:8181/api/faculty", FacultyFeModel[].class);
		return Arrays.asList(faculties.getBody());
	}
	
}
