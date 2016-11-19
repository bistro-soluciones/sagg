package com.bistro.sagg.core.osgi.ui.utils;

public class ErrorMessageUtils {

	private static final String REQUIRED_FIELD_MSG = "El campo '{?}' es requerido.";
	private static final String ONE_REQUIRED_FIELD_MSG = "Al menos uno de los campos '{?}' es requerido.";
	private static final String ONE_IN_LIST_REQUIRED_MSG = "Al menos un '{?}' es requerido.";
	private static final String WRONG_FIELD_VALUE_MSG = "El '{?}' ingresado es incorrecto.";
	private static final String WRONG_AMOUNT_FIELD_VALUE_MSG = "El '{?}' debe ser un valor monetario correcto.";

	public static String createMandatoryFieldErrorMsg(String field) {
		return REQUIRED_FIELD_MSG.replace("{?}", field);
	}

	public static String createAtLeastOneMandatoryFieldErrorMsg(String... fields) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			if (sb.length() == 0) {
				sb.append("'");
			} else {
				sb.append(", '");
			}
			sb.append(fields[i]);
			sb.append("'");
		}
		return ONE_REQUIRED_FIELD_MSG.replace("{?}", sb.toString());
	}

	public static String createOneMandatoryListElementErrorMsg(String field) {
		return ONE_IN_LIST_REQUIRED_MSG.replace("{?}", field);
	}
	
	public static String createWrongAmountFieldValueErrorMsg(String field) {
		return WRONG_AMOUNT_FIELD_VALUE_MSG.replace("{?}", field);
	}
	
	public static String createWrongFieldValueErrorMsg(String field) {
		return WRONG_FIELD_VALUE_MSG.replace("{?}", field);
	}

}
