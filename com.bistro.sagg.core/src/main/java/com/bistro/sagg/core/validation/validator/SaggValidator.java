package com.bistro.sagg.core.validation.validator;

public abstract class SaggValidator {

	protected static final String GENERIC_ERROR_MSG = "Error en validación.";
	private Object value;
	private String customErrorMsg;

	public SaggValidator(Object value) {
		super();
		this.value = value;
	}

	public SaggValidator(Object value, String customErrorMsg) {
		super();
		this.value = value;
		this.customErrorMsg = customErrorMsg;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getCustomErrorMsg() {
		return customErrorMsg;
	}

	public void setCustomErrorMsg(String customErrorMsg) {
		this.customErrorMsg = customErrorMsg;
	}

	public abstract boolean validate();

	public String getValidationError() {
		if (customErrorMsg != null) {
			return customErrorMsg;
		}
		return GENERIC_ERROR_MSG;
	}

}
