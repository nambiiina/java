package com.etech.microservice.micro_a.contrainte.errors;

public class GetObjectException extends TechnicalException {

	private static final long serialVersionUID = -1869367503866690648L;

	public GetObjectException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public GetObjectException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
