package com.elearning.fe.util.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.util.IAuthenticationFacade;
import com.elearning.fe.util.IRestCaller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestCaller implements IRestCaller {
	
	@Autowired
	private RestOperations rest;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	public <T> ResponseEntity<T> getExchange(String url,
			Class<T> responseType) {
		return exchange(url, HttpMethod.GET, new HttpEntity<Object>(null, null), responseType);
	}
	
	public <T, K> ResponseEntity<T> postExchange(String url, HttpEntity<K> requestEntity,
			Class<T> responseType) {
		return exchange(url, HttpMethod.POST, requestEntity, responseType);
	}
	
	
	private <T, K> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<K> requestEntity,
			Class<T> responseType, Object... uriVariables) {
	
		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer " + authenticationFacade.getToken() );
		headers.addAll(requestEntity.getHeaders());
		
		log.info("Request headers: ");
		headers.forEach((key, value) -> {
	        log.info(String.format(
	          "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
	    });
		
		log.info("Request body: ");
		printBody(requestEntity.getBody());
		
		HttpEntity<K> entity = new HttpEntity<>(requestEntity.getBody(), headers);
			
		ResponseEntity<T> response = rest.exchange(url, method, entity , responseType, uriVariables);
		
		log.info("Response body: ");
		printBody(response.getBody());
		
		return response;

	}
	
	private <T, K> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<K> requestEntity,
			Class<T> responseType) {
		return exchange(url, method, requestEntity, responseType, new Object[0]);
	}
	
	private void printBody(Object object) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    log.info(gson.toJson(object));
	}
	
	private <K> void printApiCallInformation(String url, HttpMethod method, HttpEntity<K> entity) {
		log.info("Request headers: ");
		entity.getHeaders().forEach((key, value) -> {
	        log.info(String.format(
	          "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
	    });
		
		
		
		if(method != null) {
			log.info("Request Method: " + method.toString());
		}
		
		if(!StringUtils.isEmpty(url)) {
			log.info("Request URI: " + url);
		}
		
		
		
	}
	
}
