package com.bistro.sagg.core.transformer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import com.bistro.sagg.core.builders.BillingItemBuilder;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.billing.BillingDocument;

public class PurchaseOrderToBillindDocumentTransformer implements ITransformer<PurchaseOrder, BillingDocument> {

	public BillingDocument transform(PurchaseOrder order) {
		BillingDocument document = new BillingDocument();
		document.setDate(Date.from(Instant.now()));
		document.setDocumentNumber(order.getOrderNumber());
		for (PurchaseOrderItem item : order.getItems()) {
			BillingItemBuilder builder = new BillingItemBuilder();
			BigDecimal unitPrice = item.getPurchaseUnitPrice();
			builder.build(item.getProduct(), item.getQuantity(), unitPrice, false);
			document.addItem(builder.getItem());
		}
		return document;
	}

}
