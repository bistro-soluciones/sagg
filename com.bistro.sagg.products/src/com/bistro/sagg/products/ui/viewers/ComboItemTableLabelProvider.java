package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.ComboItem;
import com.bistro.sagg.core.model.products.RecipeLine;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.ComboItemColumnIndexConstants;
import com.bistro.sagg.products.ui.utils.RecipeLineColumnIndexConstants;

public class ComboItemTableLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ComboItem comboItem = (ComboItem) element;
		switch (columnIndex) {
		case ComboItemColumnIndexConstants.PRODUCT_NAME_COLUMN_IDX:
			return comboItem.getItemName();
		case ComboItemColumnIndexConstants.PRODUCT_QUANTITY_COLUMN_IDX:
			return String.valueOf(comboItem.getQuantity());
		}
		return "";
	}

}
