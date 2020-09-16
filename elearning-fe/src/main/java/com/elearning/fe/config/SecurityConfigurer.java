package com.elearning.fe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

@Service
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProviderService authenticationProviderService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProviderService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/css/**").permitAll()
			.and().authorizeRequests().antMatchers("/fonts/**").permitAll()
			.and().authorizeRequests().antMatchers("/js/**").permitAll()
			.and().authorizeRequests().antMatchers("/vendor/**").permitAll()
			.and().authorizeRequests().antMatchers("/img/**").permitAll()
			.and().authorizeRequests().antMatchers("/signup").permitAll()
			.and().authorizeRequests().antMatchers("/test/questions").permitAll()
			

			.antMatchers("/login").permitAll()
			.anyRequest().authenticated().and().formLogin().loginPage("/login")

			.and().logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
			.permitAll();
	}
	
}
