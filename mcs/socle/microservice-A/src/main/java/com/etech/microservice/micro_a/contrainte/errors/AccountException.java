package com.etech.microservice.micro_a.contrainte.errors;

public class AccountException extends FunctionalException {

	private static final long serialVersionUID = 561848933805315226L;

	public AccountException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public AccountException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
