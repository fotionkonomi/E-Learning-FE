package com.elearning;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

@Service
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/**").permitAll()
			.anyRequest().authenticated();
	}
}
