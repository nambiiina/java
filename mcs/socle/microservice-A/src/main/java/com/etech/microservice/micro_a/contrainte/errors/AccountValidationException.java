package com.etech.microservice.micro_a.contrainte.errors;

public class AccountValidationException extends FunctionalException {

	private static final long serialVersionUID = 8267299710650589227L;

	public AccountValidationException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public AccountValidationException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
