package com.example.Service.exceptions;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.Service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourcenotFoundException(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		ApiResponse response = ApiResponse.builder().message(message).sucess(true).status(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
	
}
