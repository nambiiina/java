package com.etech.microservice.micro_a.contrainte.errors;

import java.util.HashMap;
import java.util.Map;

public enum ErrorsEnumExternal {

	ERR_MCS_MEDIA_NOT_FOUND("ERR_MCS_MEDIA_00010", "Media not found.", true, false),




;



	private final String errorCode;
	private final String errorMessage;
	private final Boolean error;
	private final Boolean warning;

	/**
	 * liste de correspondance entre un enum et errorCode
	 */
	private static final Map<String, ErrorsEnumExternal> BYID = new HashMap<String, ErrorsEnumExternal>();
	static {
		for (ErrorsEnumExternal e : ErrorsEnumExternal.values()) {
			if (BYID.put(e.getErrorCode(), e) != null) {
				throw new IllegalArgumentException("duplicate id: " + e.getErrorCode());
			}
		}
	}

	ErrorsEnumExternal(String errorCode, String errorMessage, Boolean error, Boolean warning) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.error = error;
		this.warning = warning;
	}

	@Override
	public String toString() {
		return "ErrorCode : " + errorCode + " errorMessage : " + errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public static ErrorsEnumExternal getById(String id) {
		return BYID.get(id);
	}

	public Boolean getError() {
		return error;
	}

	public Boolean getWarning() {
		return warning;
	}

}
