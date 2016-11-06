package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Recipe;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.products.ui.utils.RecipeColumnIndex;

public class RecipeListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		Recipe recipe1 = (Recipe) e1;
		Recipe recipe2 = (Recipe) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
		case RecipeColumnIndex.NAME_COLUMN_IDX:
			result = recipe1.getName().compareTo(recipe2.getName());
			break;
		case RecipeColumnIndex.DESCRIPTION_COLUMN_IDX:
			result = recipe1.getDescription().compareTo(recipe2.getDescription());
			break;
		case RecipeColumnIndex.CATEGORY_COLUMN_IDX:
			result = recipe1.getCategory().getName().compareTo(recipe2.getCategory().getName());
			break;
		case RecipeColumnIndex.SALES_PRICE_COLUMN_IDX:
			result = recipe1.getUnitSalesPrice().compareTo(recipe2.getUnitSalesPrice());
			break;
		}

		return result;
	}

}
