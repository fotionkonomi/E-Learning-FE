package com.elearning.fe.util.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.UniversityFeModel;

@Component
public class UniversityByIdConverter implements Converter<String, UniversityFeModel> {

	@Autowired
	private RestOperations rest;

	@Override
	public UniversityFeModel convert(String source) {
		if(StringUtils.isEmpty(source)) {
			return null;
		}
		ResponseEntity<UniversityFeModel> universityResponse = rest.getForEntity("http://localhost:8181/api/university/" + source, UniversityFeModel.class);
		if(universityResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
			return null;
		}
		
		return universityResponse.getBody();
	}
}
