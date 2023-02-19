package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class PackAlreadyExistException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public PackAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public PackAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
