package com.bistro.sagg.core.util;

import java.math.BigDecimal;
import java.util.List;

import com.bistro.sagg.core.model.order.SaleOrderItem;

public class TransactionUtils {

	public static BigDecimal addItemAmounts(List<SaleOrderItem> items) {
		BigDecimal result = BigDecimal.ZERO;
		for (SaleOrderItem item : items) {
			result = result.add(item.getAmount());
		}
		return result;
	}
	
}
