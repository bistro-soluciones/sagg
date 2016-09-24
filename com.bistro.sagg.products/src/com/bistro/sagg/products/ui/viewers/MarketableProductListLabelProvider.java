package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.MarketableProductColumnIndex;

public class MarketableProductListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		MarketableProduct product = (MarketableProduct) element;
		switch (columnIndex) {
		case MarketableProductColumnIndex.NAME_COLUMN_IDX:
			return product.getName();
		case MarketableProductColumnIndex.CATEGORY_COLUMN_IDX:
			return product.getCategory().getName();
		case MarketableProductColumnIndex.STOCK_COLUMN_IDX:
			return String.valueOf(product.getStock());
		case MarketableProductColumnIndex.STOCK_MIN_COLUMN_IDX:
			return String.valueOf(product.getMinStock());
		case MarketableProductColumnIndex.UNIT_PRICE_COLUMN_IDX:
			return String.valueOf(product.getUnitPrice());
		case MarketableProductColumnIndex.UNIT_SALES_PRICE_COLUMN_IDX:
			return String.valueOf(product.getUnitSalesPrice());
		}
		return "";
	}

}
