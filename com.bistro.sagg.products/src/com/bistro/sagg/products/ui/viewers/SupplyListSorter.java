package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.products.ui.utils.MarketableProductColumnIndex;

public class SupplyListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		Supply supply1 = (Supply) e1;
		Supply supply2 = (Supply) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
		case MarketableProductColumnIndex.NAME_COLUMN_IDX:
			result = supply1.getName().compareTo(supply2.getName());
			break;
		case MarketableProductColumnIndex.CATEGORY_COLUMN_IDX:
			result = supply1.getCategory().getName().compareTo(supply2.getCategory().getName());
			break;
		case MarketableProductColumnIndex.STOCK_COLUMN_IDX:
			result = Integer.valueOf(supply1.getStock()).compareTo(supply2.getStock());
			break;
		case MarketableProductColumnIndex.STOCK_MIN_COLUMN_IDX:
			result = Integer.valueOf(supply1.getMinStock()).compareTo(supply2.getMinStock());
			break;
		}

		return result;
	}

}
