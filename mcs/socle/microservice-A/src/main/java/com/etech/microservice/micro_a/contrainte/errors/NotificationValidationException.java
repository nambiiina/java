package com.etech.microservice.micro_a.contrainte.errors;

import org.springframework.validation.Errors;


public class NotificationValidationException extends ValidationException{
	private static final long serialVersionUID = 1L;
	
	public NotificationValidationException(Errors errors) {
		super(errors);
	}
	
	
}
