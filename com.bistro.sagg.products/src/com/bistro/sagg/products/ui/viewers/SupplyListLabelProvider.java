package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.MarketableProductColumnIndex;

public class SupplyListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Supply supply = (Supply) element;
		switch (columnIndex) {
		case MarketableProductColumnIndex.NAME_COLUMN_IDX:
			return supply.getName();
		case MarketableProductColumnIndex.CATEGORY_COLUMN_IDX:
			return supply.getCategory().getName();
		case MarketableProductColumnIndex.STOCK_COLUMN_IDX:
			return String.valueOf(supply.getStock());
		case MarketableProductColumnIndex.STOCK_MIN_COLUMN_IDX:
			return String.valueOf(supply.getMinStock());
		}
		return "";
	}

}
