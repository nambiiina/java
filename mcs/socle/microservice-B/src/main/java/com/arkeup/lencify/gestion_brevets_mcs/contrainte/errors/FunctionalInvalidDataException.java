package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class FunctionalInvalidDataException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public FunctionalInvalidDataException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public FunctionalInvalidDataException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
