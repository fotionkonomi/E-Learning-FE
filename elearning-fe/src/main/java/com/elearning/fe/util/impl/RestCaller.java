package com.elearning.fe.util.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.util.IAuthenticationFacade;
import com.elearning.fe.util.IRestCaller;

@Service
public class RestCaller implements IRestCaller {
	
	@Autowired
	private RestOperations rest;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	public <T> ResponseEntity<T> getExchange(String url,
			Class<T> responseType) {
		return exchange(url, HttpMethod.GET, new HttpEntity<Object>(null, null), responseType);
	}
	
	public <T> ResponseEntity<T> postExchange(String url, HttpEntity<?> requestEntity,
			Class<T> responseType) {
		return exchange(url, HttpMethod.POST, requestEntity, responseType);
	}
	
	
	private <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType, Object... uriVariables) {
	
		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer " + authenticationFacade.getToken() );
		headers.addAll(requestEntity.getHeaders());
		
		return rest.exchange(url, method, new HttpEntity<>(requestEntity.getBody(), headers), responseType, uriVariables);
		
	}
	
	private <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType) {
		return exchange(url, method, requestEntity, responseType, new Object[0]);
	}

}