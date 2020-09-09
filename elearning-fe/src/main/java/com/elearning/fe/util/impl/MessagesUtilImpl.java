package com.elearning.fe.util.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import com.elearning.fe.common.constants.MessageConstants;
import com.elearning.fe.util.MessagesUtil;

import lombok.Data;

@Component
@Data
public class MessagesUtilImpl implements MessagesUtil {

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	public void init() {
		accessor = new MessageSourceAccessor(messageSource);
	}

	@Override
	public String getErrorBindingMaxSize(String maxSize) {
		return getMessageWithParams(MessageConstants.MSG_SIZE_BINDING_ERROR, new String[] { maxSize });
	}

	private String getMessageWithParams(String code, Object[] args) {
		return accessor.getMessage(code, args);
	}

	private String getMessage(String code) {
		return accessor.getMessage(code);
	}
}
