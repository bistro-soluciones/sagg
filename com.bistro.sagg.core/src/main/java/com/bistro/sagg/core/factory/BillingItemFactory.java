package com.bistro.sagg.core.factory;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.order.billing.PurchaseBillingItem;
import com.bistro.sagg.core.model.order.billing.SaleBillingItem;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.SalableProduct;

public class BillingItemFactory {

	private BillingItemFactory() {
		super();
	}

	public static PurchaseBillingItem createPurchaseBillingItem(Product product, int quantity, BigDecimal unitPrice, boolean includeTaxes) {
		PurchaseBillingItem item = new PurchaseBillingItem();
		item.setProduct(product);
		item.setQuantity(item.getQuantity() + quantity);
		item.setUnitPrice(unitPrice);
		item.setTax(0.19f);
		item.setTaxAmount(unitPrice.multiply(new BigDecimal(item.getTax())));
		return item;
	}
	
	public static SaleBillingItem createSaleBillingItem(SalableProduct product, int quantity, BigDecimal unitPrice, boolean includeTaxes) {
		SaleBillingItem item = new SaleBillingItem();
		product.addToSaleBillingItem(item);
		item.setQuantity(item.getQuantity() + quantity);
		item.setUnitPrice(unitPrice);
		item.setTax(0.19f);
		item.setTaxAmount(unitPrice.multiply(new BigDecimal(item.getTax())));
		return item;
	}

}
