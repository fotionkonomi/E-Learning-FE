package com.elearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.elearning.fe.config.ConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = ConfigProperties.class)
public class ElearningFeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningFeApplication.class, args);
	}

}
