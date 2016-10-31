package com.bistro.sagg.products.ui.viewers;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.OrderItemColumnIndexConstants;

public class OrderItemTableLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		PurchaseOrderItem item = (PurchaseOrderItem) element;
		switch (columnIndex) {
		case OrderItemColumnIndexConstants.PRODUCT_NAME_COLUMN_IDX:
			return item.getProduct().getName();
		case OrderItemColumnIndexConstants.PRODUCT_QUANTITY_COLUMN_IDX:
			return String.valueOf(item.getQuantity());
		case OrderItemColumnIndexConstants.PRODUCT_UNIT_PRICE_AMOUNT_COLUMN_IDX:
			return item.getPurchaseUnitPrice().toString();
		case OrderItemColumnIndexConstants.PRODUCT_TOTAL_AMOUNT_COLUMN_IDX:
			return item.getPurchaseUnitPrice().multiply(new BigDecimal(item.getQuantity())).toString();
		}
		return "";
	}

}
