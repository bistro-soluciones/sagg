package com.bistro.sagg.core.validation.validator;

import java.util.ArrayList;
import java.util.List;

public class ChainedValidator extends SaggValidator {

	private List<SaggValidator> validators = new ArrayList<SaggValidator>();

	public ChainedValidator(Object value) {
		super(value);
	}

	public ChainedValidator(Object value, SaggValidator... validators) {
		super(value);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

	public ChainedValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

	public ChainedValidator(Object value, String customErrorMsg, SaggValidator... validators) {
		super(value, customErrorMsg);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

	public boolean validate() {
		for (SaggValidator validator : validators) {
			if (!validator.validate()) {
				setCustomErrorMsg(validator.getValidationError());
				return false;
			}
		}
		return true;
	}

}
