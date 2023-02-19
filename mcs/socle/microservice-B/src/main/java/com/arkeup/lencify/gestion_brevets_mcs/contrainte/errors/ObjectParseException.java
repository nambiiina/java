package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class ObjectParseException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public ObjectParseException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public ObjectParseException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
