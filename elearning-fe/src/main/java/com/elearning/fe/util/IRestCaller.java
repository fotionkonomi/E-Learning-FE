package com.elearning.fe.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface IRestCaller {

	<T> ResponseEntity<T> getExchange(String url,
			Class<T> responseType);
	
	<T, K> ResponseEntity<T> postExchange(String url, HttpEntity<K> requestEntity,
			Class<T> responseType);
}
