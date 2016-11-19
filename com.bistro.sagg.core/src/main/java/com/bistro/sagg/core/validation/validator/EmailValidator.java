package com.bistro.sagg.core.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends SingleValidator {

	private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

	public EmailValidator(Object value) {
		super(value);
	}

	public EmailValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

	public boolean validate() {
		Matcher matcher = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE).matcher(getValue().toString());
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

}
