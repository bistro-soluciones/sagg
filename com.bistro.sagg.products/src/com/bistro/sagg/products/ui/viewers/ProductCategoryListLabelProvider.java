package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.osgi.ui.viewers.SaggListLabelProvider;
import com.bistro.sagg.products.ui.utils.ProductCategoryColumnIndex;

public class ProductCategoryListLabelProvider extends SaggListLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		ProductCategory category = (ProductCategory) element;
		switch (columnIndex) {
		case ProductCategoryColumnIndex.NAME_COLUMN_IDX:
			return category.getName();
		}
		return "";
	}

}
