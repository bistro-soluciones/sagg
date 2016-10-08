package com.bistro.sagg.core.builders;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.products.Product;

public class BillingItemBuilder {

	private BillingItem item;

	public BillingItemBuilder() {
		super();
		this.item = new BillingItem();
	}

	public BillingItemBuilder(BillingItem item) {
		super();
		this.item = item;
	}

	public void build(Product product, int quantity, BigDecimal unitPrice, boolean includeTaxes) {
		item.setProduct(product);
		item.setQuantity(item.getQuantity() + quantity);
		item.setUnitPrice(unitPrice);
		item.setTax(0.19f);
		item.setTaxAmount(unitPrice.multiply(new BigDecimal(this.item.getTax())));
	}

	public BillingItem getItem() {
		return item;
	}

}
