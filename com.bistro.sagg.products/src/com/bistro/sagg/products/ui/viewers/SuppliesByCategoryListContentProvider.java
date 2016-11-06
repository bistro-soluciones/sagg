package com.bistro.sagg.products.ui.viewers;

import java.util.Collections;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ProductServices;

public class SuppliesByCategoryListContentProvider extends SaggStructuredContentProvider {

	private ProductCategory category;

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public void dispose() {
		this.category = null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (category != null) {
			ProductServices productServices = (ProductServices) inputElement;
			return productServices.getSuppliesByCategory(category).toArray();
		}
		return Collections.EMPTY_LIST.toArray();
	}

}
