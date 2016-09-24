package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.products.ui.utils.ProductCategoryColumnIndex;

public class ProductCategoryListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		ProductCategory category1 = (ProductCategory) e1;
		ProductCategory category2 = (ProductCategory) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
		case ProductCategoryColumnIndex.NAME_COLUMN_IDX:
			result = category1.getName().compareTo(category2.getName());
			break;
		}

		return result;
	}

}
