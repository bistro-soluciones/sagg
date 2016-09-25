package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.products.ui.utils.MarketableProductColumnIndex;

public class MarketableProductListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		MarketableProduct product1 = (MarketableProduct) e1;
		MarketableProduct product2 = (MarketableProduct) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
		case MarketableProductColumnIndex.NAME_COLUMN_IDX:
			result = product1.getName().compareTo(product2.getName());
			break;
		case MarketableProductColumnIndex.CATEGORY_COLUMN_IDX:
			result = product1.getCategory().getName().compareTo(product2.getCategory().getName());
			break;
		case MarketableProductColumnIndex.STOCK_COLUMN_IDX:
			result = Integer.valueOf(product1.getStock()).compareTo(product2.getStock());
			break;
		case MarketableProductColumnIndex.STOCK_MIN_COLUMN_IDX:
			result = Integer.valueOf(product1.getMinStock()).compareTo(product2.getMinStock());
			break;
		case MarketableProductColumnIndex.UNIT_SALES_PRICE_COLUMN_IDX:
			result = product1.getUnitSalesPrice().compareTo(product2.getUnitSalesPrice());
			break;
		}

		return result;
	}

}
