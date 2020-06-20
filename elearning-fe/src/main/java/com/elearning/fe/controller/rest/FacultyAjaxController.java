package com.elearning.fe.controller.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.FacultyFeModel;

@RestController
@RequestMapping("/faculty")
public class FacultyAjaxController {

	@Autowired
	private RestOperations rest;
	
	@GetMapping("/university/{universityId}")
	public List<FacultyFeModel> facultiesOfAUniversity(@PathVariable("universityId") Long universityId) throws URISyntaxException {
		HttpEntity<FacultyFeModel[]> faculties = rest.exchange(RequestEntity.get(new URI("http://localhost:8181/api/faculty/university/" + universityId)).build(), FacultyFeModel[].class);
		return Arrays.asList(faculties.getBody());
	}
	
	@GetMapping
	public List<FacultyFeModel> faculties() throws URISyntaxException {
		HttpEntity<FacultyFeModel[]> faculties = rest.exchange(RequestEntity.get(new URI("http://localhost:8181/api/faculty")).build(), FacultyFeModel[].class);
		return Arrays.asList(faculties.getBody());
	}
	
}
