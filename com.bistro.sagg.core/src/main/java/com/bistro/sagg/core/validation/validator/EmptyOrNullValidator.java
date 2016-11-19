package com.bistro.sagg.core.validation.validator;

import java.util.List;

public class EmptyOrNullValidator extends SingleValidator {

	public EmptyOrNullValidator(Object value) {
		super(value);
	}

	public EmptyOrNullValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

	public boolean validate() {
		if (getValue() == null) {
			return false;
		}
		if (getValue() instanceof List<?> && ((List<?>) getValue()).isEmpty()) {
			return false;
		}
		if (getValue() instanceof String && "".equals(getValue().toString())) {
			return false;
		}
		return true;
	}

}
