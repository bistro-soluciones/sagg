package com.bistro.sagg.core.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AmountValidator extends SingleValidator {

	private static final String AMOUNT_REGEX = "^\\d+(\\.\\d{1,2})?$";

	public AmountValidator(Object value) {
		super(value);
	}

	public AmountValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

	public boolean validate() {
		Matcher matcher = Pattern.compile(AMOUNT_REGEX, Pattern.CASE_INSENSITIVE).matcher(getValue().toString());
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

}
