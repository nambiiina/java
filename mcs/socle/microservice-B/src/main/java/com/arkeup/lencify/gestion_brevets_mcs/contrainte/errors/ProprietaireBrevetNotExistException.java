package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class ProprietaireBrevetNotExistException extends FunctionalException {
	private static final long serialVersionUID = 1L;

	public ProprietaireBrevetNotExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ProprietaireBrevetNotExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
