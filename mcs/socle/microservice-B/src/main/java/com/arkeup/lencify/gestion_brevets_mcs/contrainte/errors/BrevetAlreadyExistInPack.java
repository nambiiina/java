package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class BrevetAlreadyExistInPack extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public BrevetAlreadyExistInPack(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public BrevetAlreadyExistInPack(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
