package com.elearning.fe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import com.elearning.fe.model.BaseClassModel;
import com.elearning.fe.util.IAuthenticationFacade;
import com.elearning.fe.util.impl.ControllerUtil;
import com.elearning.fe.util.impl.RestCaller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public abstract class AbstractController<T extends BaseClassModel> {
	
	@Autowired
	private ControllerUtil controllerUtil;
	
	@Autowired
	protected IAuthenticationFacade authenticationFacade;
	
	@Autowired
	protected RestCaller rest;
	
	private Class<T> clazz;
	
	@Value("${remote.service.rootUri}")
	private String remoteUri;
	
	public AbstractController(Class<T> clazz) {
		this.clazz = clazz;
	}

	@GetMapping("/add")
	public String addPage(Model model) {
		return controllerUtil.getAddPage(clazz);
	}

	public String add(T model, Errors errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
			return controllerUtil.getAddPage(clazz);
		}
		
		HttpEntity<T> body = new HttpEntity<>(model);
		
		HttpEntity<Void> response = rest.postExchange(remoteUri + "/" + controllerUtil.getContextPage(clazz), body, Void.class);
		HttpHeaders header = response.getHeaders();
		System.out.println("Location: " + header.getLocation());
		return "redirect:/";
		
	}
	
	
	
}
