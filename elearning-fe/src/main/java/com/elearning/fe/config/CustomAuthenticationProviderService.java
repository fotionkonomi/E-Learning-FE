package com.elearning.fe.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import com.elearning.fe.model.RoleFeModel;
import com.elearning.fe.model.UserFeModel;
import com.elearning.fe.model.authentication.AuthenticationRequest;
import com.elearning.fe.model.authentication.AuthenticationResponse;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider {
	
	@Autowired
	private RestOperations restOperations;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authenticationToken = null;

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		AuthenticationRequest authRequest = new AuthenticationRequest(username, password);
		ResponseEntity<AuthenticationResponse> authResponse = restOperations.postForEntity("http://localhost:8181/api/authenticate", new HttpEntity<>(authRequest), AuthenticationResponse.class);
		String jwt = authResponse.getBody().getJwt();

		if (authResponse.getStatusCode() == HttpStatus.OK) {
			if (!StringUtils.isEmpty(jwt)) {
				HttpHeaders headers = new HttpHeaders();
				headers.set("authorization", "Bearer " + jwt);
				ResponseEntity<UserFeModel> userResponse = restOperations.postForEntity("http://localhost:8181/api/user/username", new HttpEntity<>(username, headers), UserFeModel.class);
				UserFeModel user = userResponse.getBody();
				user.setToken(jwt);
				Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
				authenticationToken = new UsernamePasswordAuthenticationToken(
						user, password, grantedAuthorities);
			}
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}

		return authenticationToken;
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(UserFeModel user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		RoleFeModel roleObject = user.getRole();
		String role = null;
		if(roleObject != null) {
			role = roleObject.getName();
		}
		if ("admin".equalsIgnoreCase(role)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if ("student".equalsIgnoreCase(role)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
		} else if("professor".equalsIgnoreCase(role)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_PROFFESOR"));
		}

		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
