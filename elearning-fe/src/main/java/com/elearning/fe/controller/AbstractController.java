package com.elearning.fe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.elearning.fe.model.BaseClassModel;
import com.elearning.fe.util.impl.ControllerUtil;

@Controller
public abstract class AbstractController<T extends BaseClassModel> {
	
	@Autowired
	private ControllerUtil<T> controllerUtil;
	
	private Class<T> clazz;
	
	public AbstractController(Class<T> clazz) {
		this.clazz = clazz;
	}

	public String addPage(Model model) {
		return controllerUtil.getAddPage();
	}
	
	
	
}
