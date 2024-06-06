package com.hexaware.policymanager.exception;

public class UserPolicyNotFoundException extends RuntimeException {
    public UserPolicyNotFoundException(String message) {
        super(message);
    }
}