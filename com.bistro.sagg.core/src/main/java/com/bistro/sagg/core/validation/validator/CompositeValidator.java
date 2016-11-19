package com.bistro.sagg.core.validation.validator;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeValidator extends SaggValidator {

	private List<SaggValidator> validators = new ArrayList<SaggValidator>();

	public CompositeValidator(Object value, SaggValidator... validators) {
		super(value);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

	public CompositeValidator(Object value, String customErrorMsg, SaggValidator... validators) {
		super(value, customErrorMsg);
		for (SaggValidator validator : validators) {
			this.validators.add(validator);
		}
	}

}
