package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

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


