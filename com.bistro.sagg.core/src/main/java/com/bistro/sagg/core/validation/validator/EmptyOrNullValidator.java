package com.bistro.sagg.core.validation.validator;

public class EmptyOrNullValidator extends SingleValidator {

	public EmptyOrNullValidator(Object value) {
		super(value);
	}

	public EmptyOrNullValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

	public boolean validate() {
		if (getValue() == null || "".equals(getValue().toString())) {
			return false;
		}
		return true;
	}

}
