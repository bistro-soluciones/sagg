package com.bistro.sagg.core.util;

import java.math.BigDecimal;
import java.util.List;

import com.bistro.sagg.core.model.order.OrderItem;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.SaleOrderItem;

public class TransactionUtils {

	public static BigDecimal addItemAmounts(List<? extends OrderItem> items) {
		BigDecimal result = BigDecimal.ZERO;
		for (OrderItem item : items) {
			if(item instanceof SaleOrderItem) {
				result = result.add(getAmount((SaleOrderItem)item));
			}
			if(item instanceof PurchaseOrderItem) {
				result = result.add(getAmount((PurchaseOrderItem)item));
			}
		}
		return result;
	}

	private static BigDecimal getAmount(SaleOrderItem item) {
		return item.getAmount();
	}
	
	private static BigDecimal getAmount(PurchaseOrderItem item) {
		return item.getPurchaseUnitPrice().multiply(new BigDecimal(item.getQuantity()));
	}
	
	public static BigDecimal calculateWithTaxes(BigDecimal amount) {
		// TODO tax % en sesion
		return amount.multiply(new BigDecimal(119)).divide(new BigDecimal(100));
	}
	
}
