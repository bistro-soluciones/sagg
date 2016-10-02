package com.bistro.sagg.products.ui.viewers;

import java.util.Collections;

import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;

public class ProductCategoryComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement != null) {
			Supplier supplier = (Supplier) inputElement;
			if(supplier.getCategories() != null)
			return supplier.getCategories().toArray();
		}
		return Collections.EMPTY_LIST.toArray();
	}

}
