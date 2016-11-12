package com.bistro.sagg.core.transformer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.bistro.sagg.core.factory.BillingDocumentFactory;
import com.bistro.sagg.core.factory.BillingItemFactory;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.order.billing.BillingDocument;
import com.bistro.sagg.core.model.order.billing.SaleBillingDocument;
import com.bistro.sagg.core.model.order.billing.SaleBillingItem;

public class SaleOrderToBillindDocumentTransformer implements ITransformer<SaleOrder, BillingDocument> {

	public SaleBillingDocument transform(SaleOrder order) {
		List<SaleBillingItem> items = new ArrayList<SaleBillingItem>();
		for (SaleOrderItem item : order.getItems()) {
			BigDecimal unitPrice = item.getAmount().divide(new BigDecimal(item.getQuantity()));
			items.add(BillingItemFactory.createSaleBillingItem(item.getSalableProduct(), item.getQuantity(), unitPrice,
					false));
		}
		SaleBillingDocument document = BillingDocumentFactory.createSaleBillingDocument(order.getBranch(), null, null,
				null, order.getSeller(), items);
		return document;
	}

}
