package com.elearning.fe.util;

public interface IControllerUtil {

	public <T> String getContextPage(Class<T> clazz);
	
	public <T> String getAddPage(Class<T> clazz);
}
