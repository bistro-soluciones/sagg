package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.ComboColumnIndex;

public class ComboListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Combo combo = (Combo) element;
		switch (columnIndex) {
		case ComboColumnIndex.NAME_COLUMN_IDX:
			return combo.getName();
		case ComboColumnIndex.DESCRIPTION_COLUMN_IDX:
			return combo.getDescription();
		case ComboColumnIndex.SALES_PRICE_COLUMN_IDX:
			return String.valueOf(combo.getUnitSalesPrice());
		}
		return "";
	}

}
