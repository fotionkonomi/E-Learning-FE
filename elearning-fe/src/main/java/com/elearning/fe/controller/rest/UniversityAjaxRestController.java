package com.elearning.fe.controller.rest;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.fe.model.UniversityFeModel;
import com.elearning.fe.util.impl.RestCaller;

@RestController
@RequestMapping("/rest/university")
public class UniversityAjaxRestController {

	@Autowired
	private RestCaller rest;

	@RequestMapping
	public List<UniversityFeModel> universities() throws URISyntaxException {
		HttpEntity<UniversityFeModel[]> universities = rest.getExchange("http://localhost:8181/api/university", UniversityFeModel[].class);
		return Arrays.asList(universities.getBody());

	}

}
