package com.etech.microservice.micro_a.contrainte.errors;

public class ObjectAlreadyExistException extends FunctionalException {

	private static final long serialVersionUID = -3082098841260343768L;

	public ObjectAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public ObjectAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
