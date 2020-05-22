package com.elearning.fe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "remote.service")
@Data
public class ConfigProperties {

	private String rootUri;

}
