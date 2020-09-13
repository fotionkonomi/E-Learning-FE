package com.elearning.fe.util.impl;

import org.springframework.stereotype.Component;

import com.elearning.fe.model.BaseClassModel;
import com.elearning.fe.util.IControllerUtil;

@Component
public class ControllerUtil<T extends BaseClassModel> implements IControllerUtil<T>{

	private Class<T> clazz;

	public ControllerUtil(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getAddPage() {
		String classSimpleName = clazz.getSimpleName();
		return classSimpleName.substring(0, classSimpleName.indexOf("FeModel"));
	}
	
}
