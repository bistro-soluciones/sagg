package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.SupplyColumnIndex;

public class SupplyListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Supply supply = (Supply) element;
		switch (columnIndex) {
		case SupplyColumnIndex.NAME_COLUMN_IDX:
			return supply.getName();
		case SupplyColumnIndex.CATEGORY_COLUMN_IDX:
			return supply.getCategory().getName();
		case SupplyColumnIndex.STOCK_COLUMN_IDX:
			return String.valueOf(supply.getStock());
		case SupplyColumnIndex.STOCK_MIN_COLUMN_IDX:
			return String.valueOf(supply.getMinStock());
		case SupplyColumnIndex.FORMAT_COLUMN_IDX:
			return supply.getFormat().getName();
		}
		return "";
	}

}
