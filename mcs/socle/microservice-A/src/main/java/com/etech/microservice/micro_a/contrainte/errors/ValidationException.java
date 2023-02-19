package com.etech.microservice.micro_a.contrainte.errors;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 269L;

	private Errors errors;

	public ValidationException(Errors errors) {
		super();
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}
}


