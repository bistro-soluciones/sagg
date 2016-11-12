package com.bistro.sagg.core.factory;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.SalableProduct;

public class OrderItemFactory {

	private OrderItemFactory() {
		super();
	}

	public static PurchaseOrderItem createPurchaseOrderItem(Product product, int quantity, BigDecimal purchaseUnitPrice) {
		PurchaseOrderItem item = new PurchaseOrderItem();
		item.setProduct(product);
		item.setQuantity(quantity);
		item.setPurchaseUnitPrice(purchaseUnitPrice);
		item.setAvailableStock(quantity * product.getFormat().getUnit());
		item.recalculateAmount();
		return item;
	}

	public static SaleOrderItem createSaleOrderItem(SalableProduct product, int quantity) {
		SaleOrderItem item = new SaleOrderItem();
		item.setSalableProduct(product);
		item.setQuantity(quantity);
		item.recalculateAmount();
		return item;
	}
	
}