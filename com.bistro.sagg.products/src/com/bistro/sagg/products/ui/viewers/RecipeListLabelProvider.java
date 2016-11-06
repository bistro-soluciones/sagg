package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Recipe;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.products.ui.utils.RecipeColumnIndex;

public class RecipeListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Recipe recipe = (Recipe) element;
		switch (columnIndex) {
		case RecipeColumnIndex.NAME_COLUMN_IDX:
			return recipe.getName();
		case RecipeColumnIndex.DESCRIPTION_COLUMN_IDX:
			return recipe.getDescription();
		case RecipeColumnIndex.CATEGORY_COLUMN_IDX:
			return recipe.getCategory().getName();
		case RecipeColumnIndex.SALES_PRICE_COLUMN_IDX:
			return String.valueOf(recipe.getUnitSalesPrice());
		}
		return "";
	}

}
