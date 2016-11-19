package com.bistro.sagg.core.validation.validator;

import java.util.ArrayList;
import java.util.List;

public class AndValidator extends CompositeValidator {

	private List<SaggValidator> validators = new ArrayList<SaggValidator>();

	public AndValidator(Object value, SaggValidator... validators) {
		super(value);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

	public AndValidator(Object value, String customErrorMsg, SaggValidator... validators) {
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
