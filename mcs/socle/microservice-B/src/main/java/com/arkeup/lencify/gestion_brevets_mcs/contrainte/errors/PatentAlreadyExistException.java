package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class PatentAlreadyExistException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public PatentAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public PatentAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
