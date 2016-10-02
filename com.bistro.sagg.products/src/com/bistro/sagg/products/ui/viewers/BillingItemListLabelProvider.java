package com.bistro.sagg.products.ui.viewers;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.billing.BillingItem;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.BillingItemColumnIndex;

public class BillingItemListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		BillingItem item = (BillingItem) element;
		switch (columnIndex) {
		case BillingItemColumnIndex.NAME_COLUMN_IDX:
			return item.getProduct().getName();
		case BillingItemColumnIndex.CATEGORY_COLUMN_IDX:
			return item.getProduct().getCategory().getName();
		case BillingItemColumnIndex.QUANTITY_COLUMN_IDX:
			return String.valueOf(item.getQuantity());
		case BillingItemColumnIndex.UNIT_PRICE_COLUMN_IDX:
			return String.valueOf(item.getUnitPrice());
		case BillingItemColumnIndex.TOTAL_COLUMN_IDX:
			return String.valueOf(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
		}
		return "";
	}

}
