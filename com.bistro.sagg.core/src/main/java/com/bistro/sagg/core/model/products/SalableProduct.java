package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.order.billing.SaleBillingItem;

public interface SalableProduct {

	BigDecimal getUnitSalesPrice();
	
	void addToSaleOrderItem(SaleOrderItem item);

	void addToComboItem(ComboItem item);
	
	void addToSaleBillingItem(SaleBillingItem item);
	
	String getName();
	
	boolean hasStock();

	void decreaseStock(int quantity);

}
