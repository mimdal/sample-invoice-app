package com.github.mimdal.exception;

public class EntitiesNotRelatedException extends BusinessException{

	public EntitiesNotRelatedException(String message) {
		super(message);
	}

	public EntitiesNotRelatedException(String message, Throwable cause) {
		super(message, cause);
	}
}
