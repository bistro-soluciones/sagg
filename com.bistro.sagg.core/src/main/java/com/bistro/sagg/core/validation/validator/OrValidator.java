package com.bistro.sagg.core.validation.validator;

import java.util.ArrayList;
import java.util.List;

public class OrValidator extends CompositeValidator {

	private List<SaggValidator> validators = new ArrayList<SaggValidator>();

	public OrValidator(Object value, SaggValidator... validators) {
		super(value);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

	public OrValidator(Object value, String customErrorMsg, SaggValidator... validators) {
		super(value, customErrorMsg);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

	public boolean validate() {
		for (SaggValidator validator : validators) {
			if (validator.validate()) {
				return true;
			}
		}
		return false;
	}

}
