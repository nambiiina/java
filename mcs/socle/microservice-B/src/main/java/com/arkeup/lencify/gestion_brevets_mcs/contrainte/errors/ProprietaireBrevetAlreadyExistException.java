package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class ProprietaireBrevetAlreadyExistException extends FunctionalException {
	private static final long serialVersionUID = 1L;

	public ProprietaireBrevetAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ProprietaireBrevetAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
