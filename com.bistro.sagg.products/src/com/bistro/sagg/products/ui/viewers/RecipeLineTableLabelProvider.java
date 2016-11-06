package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.RecipeLine;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.RecipeLineColumnIndexConstants;

public class RecipeLineTableLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		RecipeLine recimeLine = (RecipeLine) element;
		switch (columnIndex) {
		case RecipeLineColumnIndexConstants.SUPPLY_NAME_COLUMN_IDX:
			return recimeLine.getSupply().getName();
		case RecipeLineColumnIndexConstants.SUPPLY_PORTION_COLUMN_IDX:
			return String.valueOf(recimeLine.getPortion());
		}
		return "";
	}

}
