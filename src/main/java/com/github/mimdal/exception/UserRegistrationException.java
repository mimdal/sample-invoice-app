package com.github.mimdal.exception;

public class UserRegistrationException extends BusinessException {

	public UserRegistrationException(String message) {
		super(message);
	}

	public UserRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}
}
