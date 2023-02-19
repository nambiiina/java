package com.etech.microservice.micro_a.contrainte.errors;

public class EmailAlreadyExistException extends FunctionalException {

	private static final long serialVersionUID = -5770874775382061757L;

	public EmailAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public EmailAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
