package com.bistro.sagg.core.generator;

import com.bistro.sagg.core.model.company.FranchiseBranch;

public class SaleOrderNumberGenerator {

	private static String SEPARATOR = "-";
	private static String BASE_ORDER_UMBER = "00000000";

	public static String generateOrderNumber(FranchiseBranch branch, Long lastOrderNumber) {
		StringBuilder sb = new StringBuilder();
		sb.append(branch.getCode());
		sb.append(SEPARATOR);
		sb.append(branch.getFranchise().getCode());
		sb.append(SEPARATOR);
		String newOrderNumber = calculateNewOrderNumber(lastOrderNumber);
		sb.append(BASE_ORDER_UMBER.substring(newOrderNumber.length()));
		sb.append(newOrderNumber);
		return sb.toString();
	}

	private static String calculateNewOrderNumber(Long lastOrderNumber) {
		if (lastOrderNumber == null) {
			return "1";
		} else {
			return String.valueOf(lastOrderNumber + 1);
		}
	}

}
