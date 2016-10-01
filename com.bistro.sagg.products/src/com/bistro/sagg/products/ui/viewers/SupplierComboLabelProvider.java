package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class SupplierComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Supplier) {
			return ((Supplier) element).getBusinessName();
		}
		return element.toString();
	}

}
