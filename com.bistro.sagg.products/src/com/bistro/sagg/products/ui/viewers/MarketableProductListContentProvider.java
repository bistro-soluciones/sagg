package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ProductServices;

public class MarketableProductListContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		ProductServices productServices = (ProductServices) inputElement;
		return productServices.getMarketableProducts().toArray();
	}

}
