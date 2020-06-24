package com.elearning.fe.util.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.BaseClassModel;

public abstract class ByIdConverter<T extends BaseClassModel> implements Converter<String, T> {

	@Autowired
	protected RestOperations rest;

	private Class<T> modelClass;
	
	public ByIdConverter(Class<T> modelClass) {
		this.modelClass = modelClass;
	}
	
	@Override
	public T convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		ResponseEntity<T> response = rest.getForEntity("http://localhost:8181/api/" + nameOfModelClass() + "/" + source,
				modelClass);
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}

		return response.getBody();
	}

	private String nameOfModelClass() {
		return modelClass.getSimpleName().replace("FeModel", "").toLowerCase();
	}
}
