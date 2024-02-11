package com.hexaware.policymanager.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<String> handlePolicyNotFoundException(PolicyNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserPolicyNotFoundException.class)
	public ResponseEntity<Object> handleUserPolicyNotFoundException(UserPolicyNotFoundException ex) {
		String errorMessage = ex.getMessage();
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClaimNotFoundException.class)
	public ResponseEntity<String> handleClaimNotFoundException(ClaimNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PolicyRegisteredByUserException.class)
	public ResponseEntity<String> handlePolicyRegisteredByUserException(PolicyRegisteredByUserException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<String> handleDuplicateUserException(DuplicateUserException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

}