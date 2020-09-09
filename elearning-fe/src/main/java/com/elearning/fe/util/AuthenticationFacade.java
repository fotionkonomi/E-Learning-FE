package com.elearning.fe.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.elearning.fe.model.UserFeModel;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Override
	public UserFeModel getAuthenticatedUser() {
		return (UserFeModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public String getToken() {
		return getAuthenticatedUser().getToken();
	}

}
