package com.bistro.sagg.sales.ui.viewers;

import com.bistro.sagg.core.model.products.SalableProduct;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class SalableProductListLabelProvider extends SaggLabelProvider {

	private static final String WITHOUT_STOCK = " (SIN STOCK)";

	@Override
	public String getText(Object element) {
		if (element instanceof SalableProduct) {
			SalableProduct product = (SalableProduct) element;
			if(product.hasStock()) {
				return product.getName();
			} else {
				return product.getName() + WITHOUT_STOCK;
			}
		}
		return element.toString();
	}

}
