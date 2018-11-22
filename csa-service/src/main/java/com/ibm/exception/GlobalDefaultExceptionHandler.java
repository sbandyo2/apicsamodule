package com.ibm.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice("com.ibm.controller")
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(HttpServletRequest req,Exception ex) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage(), ex));
	}
	
	@ExceptionHandler(value = ServiceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(HttpServletRequest req,ServiceException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST,ex.getMessage(), ex));
	}

	/**
	 * @param apiError
	 * @return
	 */
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}