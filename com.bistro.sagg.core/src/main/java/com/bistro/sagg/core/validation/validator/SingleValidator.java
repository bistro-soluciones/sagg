package com.bistro.sagg.core.validation.validator;

public abstract class SingleValidator extends SaggValidator {

	public SingleValidator(Object value) {
		super(value);
	}

	public SingleValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

}
