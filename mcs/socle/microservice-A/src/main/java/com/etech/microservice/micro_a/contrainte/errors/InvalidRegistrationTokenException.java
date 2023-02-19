package com.etech.microservice.micro_a.contrainte.errors;

public class InvalidRegistrationTokenException extends FunctionalException {

	private static final long serialVersionUID = 4352926586509128600L;

	public InvalidRegistrationTokenException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
	public InvalidRegistrationTokenException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

}
