package com.etech.microservice.micro_a.contrainte.errors;

public class ObjectSaveException extends TechnicalException {
	
	private static final long serialVersionUID = 69L;

	public ObjectSaveException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ObjectSaveException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
}


