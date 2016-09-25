package com.bistro.sagg.suppliers.ui.viewers;

import java.util.List;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;

public class ProductCategoryListContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((List<ProductCategory>) inputElement).toArray();
	}

}
