package com.bistro.sagg.core.validation.processor;

import java.util.List;

import com.bistro.sagg.core.validation.validator.SaggValidator;

public class ListValidatorProcessor {

	private List<SaggValidator> validators;
	private StringBuilder errorMsg = new StringBuilder();

	public ListValidatorProcessor(List<SaggValidator> validators) {
		super();
		this.validators = validators;
	}

	public boolean processValidation() {
		boolean result = true;
		for (SaggValidator validator : validators) {
			if (!validator.validate()) {
				if (errorMsg.length() > 0) {
					errorMsg.append("\n");
				}
				errorMsg.append(validator.getValidationError());
				result = false;
			}
		}
		return result;
	}

	public String getErrorMessage() {
		return errorMsg.toString();
	}

}
