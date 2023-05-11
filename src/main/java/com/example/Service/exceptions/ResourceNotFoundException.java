package com.example.Service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super(" Resource Not Found Exception On Server ");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
}
