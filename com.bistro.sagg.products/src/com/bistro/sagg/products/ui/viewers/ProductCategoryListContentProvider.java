package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggListContentProvider;
import com.bistro.sagg.core.services.ProductServices;

public class ProductCategoryListContentProvider extends SaggListContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		ProductServices productServices = (ProductServices) inputElement;
		return productServices.getProductCategories().toArray();
	}

}
