package com.etech.microservice.micro_a.contrainte.errors;

public class DeleteObjectException extends TechnicalException {

	private static final long serialVersionUID = 1656662198926158836L;

	public DeleteObjectException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
	public DeleteObjectException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

}
