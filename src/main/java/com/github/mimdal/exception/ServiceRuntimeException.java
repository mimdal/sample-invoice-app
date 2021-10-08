package com.github.mimdal.exception;

public class ServiceRuntimeException extends RuntimeException {

	public ServiceRuntimeException(String message) {
		super(message);
	}

	public ServiceRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
