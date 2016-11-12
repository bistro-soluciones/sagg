package com.bistro.sagg.core.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bistro.sagg.core.factory.BillingDocumentFactory;
import com.bistro.sagg.core.factory.BillingItemFactory;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.billing.BillingDocument;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingDocument;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingItem;

public class PurchaseOrderToBillindDocumentTransformer implements ITransformer<PurchaseOrder, BillingDocument> {

	public PurchaseBillingDocument transform(PurchaseOrder order) {
		List<PurchaseBillingItem> items = new ArrayList<PurchaseBillingItem>();
		for (PurchaseOrderItem item : order.getItems()) {
			BigDecimal unitPrice = item.getAmount().divide(new BigDecimal(item.getQuantity()));
			items.add(BillingItemFactory.createPurchaseBillingItem(item.getProduct(), item.getQuantity(), unitPrice,
					false));
		}
		PurchaseBillingDocument document = BillingDocumentFactory.createPurchaseBillingDocument(order.getBranch(), null,
				null, null, order.getSupplier(), items);
		return document;
	}

}
