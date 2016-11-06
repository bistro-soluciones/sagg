package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.products.ui.utils.ComboColumnIndex;

public class ComboListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		Combo combo1 = (Combo) e1;
		Combo combo2 = (Combo) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
		case ComboColumnIndex.NAME_COLUMN_IDX:
			result = combo1.getName().compareTo(combo2.getName());
			break;
		case ComboColumnIndex.DESCRIPTION_COLUMN_IDX:
			result = combo1.getDescription().compareTo(combo2.getDescription());
			break;
		case ComboColumnIndex.SALES_PRICE_COLUMN_IDX:
			result = combo1.getUnitSalesPrice().compareTo(combo2.getUnitSalesPrice());
			break;
		}

		return result;
	}

}
