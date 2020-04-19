package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ElearningFeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningFeApplication.class, args);
	}
	
	@Bean
	public RestOperations rest() {
		return new RestTemplate();
	}

}
