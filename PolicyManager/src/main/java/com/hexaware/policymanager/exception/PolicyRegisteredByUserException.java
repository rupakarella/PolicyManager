package com.hexaware.policymanager.exception;

public class PolicyRegisteredByUserException extends Exception {
	public PolicyRegisteredByUserException(String message) {
		super(message);
	}

	public PolicyRegisteredByUserException(String message, Throwable cause) {
		super(message, cause);
	}
}