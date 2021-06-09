package com.orange.proposta.orange.exception;

import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handle(NoSuchElementException exception){
		return ResponseEntity.badRequest().build();
	}
}
