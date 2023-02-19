/**
 *
 */
package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

/**
 * @author Samba
 *
 */
public class CallApiEspaceNetException extends FunctionalException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public CallApiEspaceNetException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public CallApiEspaceNetException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
