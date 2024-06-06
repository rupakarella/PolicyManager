package com.hexaware.policymanager.exception;

public class PolicyNotFoundException extends RuntimeException {
	public PolicyNotFoundException(String message) {
		super(message);
	}

	public PolicyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}