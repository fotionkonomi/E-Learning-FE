package com.elearning.fe.util.impl;

import org.springframework.stereotype.Component;

import com.elearning.fe.util.IControllerUtil;

@Component
public class ControllerUtil implements IControllerUtil {

	@Override
	public <T> String getContextPage(Class<T> clazz) {
		String classSimpleName = clazz.getSimpleName();
		return classSimpleName.substring(0, classSimpleName.indexOf("FeModel")).toLowerCase();
	}
	
	public <T> String getAddPage(Class<T> clazz) {
		return (getContextPage(clazz) + "/add-" + getContextPage(clazz)).toLowerCase();
	}
	
}
