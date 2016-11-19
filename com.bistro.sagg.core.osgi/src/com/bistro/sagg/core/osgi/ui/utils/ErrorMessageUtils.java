package com.bistro.sagg.core.osgi.ui.utils;

public class ErrorMessageUtils {

	private static final String REQUIRED_FIELD_MSG = "El campo '{?}' es requerido.";
	private static final String WRONG_FIELD_VALUE_MSG = "El '{?}' ingresado es incorrecto.";

	public static String createMandatoryFieldErrorMsg(String field) {
		return REQUIRED_FIELD_MSG.replace("{?}", field);
	}

	public static String createWrongFieldValueErrorMsg(String field) {
		return WRONG_FIELD_VALUE_MSG.replace("{?}", field);
	}

}
