package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class ProductListLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Product) {
			return ((Product) element).getName();
		}
		return element.toString();
	}

}
