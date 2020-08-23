package com.elearning.fe.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.elearning.fe.common.enums.GenderEnum;
import com.elearning.fe.model.UserFeModel;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SignupController {
	
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
		
		return "redirect:/";
	}
}
