package com.hexaware.policymanager.exception;

public class PaymentNotFoundException extends RuntimeException {
	public PaymentNotFoundException(String message) {
		super(message);
	}
}
