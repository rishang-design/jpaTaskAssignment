package com.assignment.task.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assignment.task.Helper.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException e){
		
		String message= e.getMessage();
		ApiResponse response = new ApiResponse(message , false);
		
		return new ResponseEntity<ApiResponse>(response , HttpStatus.NOT_FOUND);
		
	}

}
