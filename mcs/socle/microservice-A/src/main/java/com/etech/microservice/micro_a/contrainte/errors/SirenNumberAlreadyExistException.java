package com.etech.microservice.micro_a.contrainte.errors;

public class SirenNumberAlreadyExistException extends FunctionalException {

	private static final long serialVersionUID = 4429797734514042532L;

	public SirenNumberAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public SirenNumberAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
