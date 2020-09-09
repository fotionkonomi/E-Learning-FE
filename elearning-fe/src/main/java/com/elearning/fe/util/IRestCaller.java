package com.elearning.fe.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface IRestCaller {

	<T> ResponseEntity<T> getExchange(String url,
			Class<T> responseType);
	
	<T> ResponseEntity<T> postExchange(String url, HttpEntity<?> requestEntity,
			Class<T> responseType);
}
