package com.elearning.fe.controller.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.UniversityFeModel;

@RestController
@RequestMapping("/rest/university")
public class UniversityAjaxRestController {

	@Autowired
	private RestOperations rest;

	@RequestMapping
	public List<UniversityFeModel> universities() throws URISyntaxException {
		HttpEntity<UniversityFeModel[]> universities = rest.exchange(
				RequestEntity.get(new URI("http://localhost:8181/api/university")).build(), UniversityFeModel[].class);
		return Arrays.asList(universities.getBody());

	}

}
