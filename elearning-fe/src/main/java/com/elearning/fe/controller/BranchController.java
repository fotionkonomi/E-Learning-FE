package com.elearning.fe.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.BranchFeModel;
import com.elearning.fe.model.UniversityFeModel;
import com.elearning.fe.util.impl.RestCaller;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/branch")
@Slf4j
public class BranchController extends AbstractController<BranchFeModel> {

	public BranchController() {
		super(BranchFeModel.class);
	}

	@Autowired
	private RestCaller rest;

	@ModelAttribute(name = "branch")
	public BranchFeModel branch() {
		return new BranchFeModel();
	}
	
	@ModelAttribute(name = "universities")
	public Collection<UniversityFeModel> universities() throws RestClientException, URISyntaxException {
		HttpEntity<UniversityFeModel[]> universities = rest.getExchange("http://localhost:8181/api/university", UniversityFeModel[].class);
		return Arrays.asList(universities.getBody());
	}
	
	@SuppressWarnings("rawtypes")
	@ModelAttribute(name = "emptyList")
	public Collection emptyList() {
		return Collections.emptyList();
	}
	
	@PostMapping(value = "/add")
	public String addBranch(@Valid @ModelAttribute("branch") BranchFeModel branch, Errors errors) {
		return add(branch, errors);
	}
	
	
}
