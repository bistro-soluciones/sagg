package com.bistro.sagg.core.validation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RUTValidator extends SingleValidator {

	private static final String RUT_REGEX = "(\\d{1}|\\d{2}).\\d{3}.\\d{3}-[k|K|[0-9]]";

	public RUTValidator(Object value) {
		super(value);
	}

	public RUTValidator(Object value, String customErrorMsg) {
		super(value, customErrorMsg);
	}

	public boolean validate() {
		Matcher matcher = Pattern.compile(RUT_REGEX, Pattern.CASE_INSENSITIVE).matcher(getValue().toString());
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

}
