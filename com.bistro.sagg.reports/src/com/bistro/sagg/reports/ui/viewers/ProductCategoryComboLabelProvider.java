package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class ProductCategoryComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof ProductCategory) {
			return ((ProductCategory) element).getName();
		}
		return element.toString();
	}

}