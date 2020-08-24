package com.elearning.fe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.common.enums.GenderEnum;
import com.elearning.fe.model.UserFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {
	
	@Autowired
	private RestOperations rest;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@ModelAttribute("newUser")
	public UserFeModel newUser() {
		return new UserFeModel();
	}
	
	@ModelAttribute("genders") 
	public GenderEnum[] genders() {
		return GenderEnum.values();
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(@ModelAttribute("newUser") @Valid UserFeModel user, Errors errors) {
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(error -> log.error(error.getArguments() + error.getDefaultMessage()));
			return "signup";
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		HttpEntity<Void> response = rest.exchange("http://localhost:8181/api/user", HttpMethod.POST, new HttpEntity<UserFeModel>(user), Void.class);
		
		return "redirect:/";
	}
}
