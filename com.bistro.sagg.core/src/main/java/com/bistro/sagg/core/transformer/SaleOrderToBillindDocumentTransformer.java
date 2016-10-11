package com.bistro.sagg.core.transformer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import com.bistro.sagg.core.builders.BillingItemBuilder;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.order.billing.BillingDocument;

public class SaleOrderToBillindDocumentTransformer implements ITransformer<SaleOrder, BillingDocument> {

	public BillingDocument transform(SaleOrder order) {
		BillingDocument document = new BillingDocument();
		document.setDate(Date.from(Instant.now()));
		document.setDocumentNumber(order.getOrderNumber());
		for (SaleOrderItem item : order.getItems()) {
			BillingItemBuilder builder = new BillingItemBuilder();
			BigDecimal unitPrice = item.getAmount().divide(new BigDecimal(item.getQuantity()));
			builder.build(item.getProduct(), item.getQuantity(), unitPrice, false);
			document.addItem(builder.getItem());
		}
		return document;
	}

}
