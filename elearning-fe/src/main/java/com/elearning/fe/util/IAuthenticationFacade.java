package com.elearning.fe.util;

import com.elearning.fe.model.UserFeModel;

public interface IAuthenticationFacade {
	
	UserFeModel getAuthenticatedUser();
	
	String getToken();

}
