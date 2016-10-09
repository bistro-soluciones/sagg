package com.bistro.sagg.core.factory;

import com.bistro.sagg.core.model.order.OrderItem;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.products.Product;

public class OrderItemFactory {

	public static PurchaseOrderItem createPurchaseOrderItem(Product product, int quantity) {
		return (PurchaseOrderItem) fillItem(new PurchaseOrderItem(), product, quantity);
	}

	public static SaleOrderItem createSaleOrderItem(Product product, int quantity) {
		SaleOrderItem item = (SaleOrderItem) fillItem(new SaleOrderItem(), product, quantity);
		item.recalculateAmount();
		return item;
	}
	
	private static OrderItem fillItem(OrderItem item, Product product, int quantity) {
		item.setProduct(product);
		item.setQuantity(quantity);
		return item;
	}

}