package com.elearning.fe.controller.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.UniversityFeModel;

@RestController
@RequestMapping("/rest/university")
public class UniversityAjaxRestController {

	@Autowired
	private RestOperations restTemplate;

	@GetMapping
	public List<UniversityFeModel> universities(@RequestParam(value = "_type", required = false) String queryParam)
			throws URISyntaxException {
			HttpEntity<UniversityFeModel[]> universities = restTemplate.exchange(RequestEntity
					.get(new URI("http://localhost:8181/api/university")).accept(MediaType.APPLICATION_JSON).build(),
					UniversityFeModel[].class);
			System.out.println("Univesitetet" +  Arrays.asList(universities.getBody()));
			return Arrays.asList(universities.getBody());
		
	}
}
