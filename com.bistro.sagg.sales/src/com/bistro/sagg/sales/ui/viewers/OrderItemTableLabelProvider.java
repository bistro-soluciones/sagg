package com.bistro.sagg.sales.ui.viewers;

import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.sales.ui.utils.OrderItemColumnIndexConstants;

public class OrderItemTableLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		SaleOrderItem item = (SaleOrderItem) element;
		switch (columnIndex) {
		case OrderItemColumnIndexConstants.PRODUCT_NAME_COLUMN_IDX:
			return item.getProduct().getName();
		case OrderItemColumnIndexConstants.PRODUCT_QUANTITY_COLUMN_IDX:
			return String.valueOf(item.getQuantity());
		case OrderItemColumnIndexConstants.PRODUCT_AMOUNT_COLUMN_IDX:
			return item.getAmount().toString();
		}
		return "";
	}

}
