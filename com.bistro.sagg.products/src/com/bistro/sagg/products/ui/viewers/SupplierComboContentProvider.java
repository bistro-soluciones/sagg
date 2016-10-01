package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.SupplierServices;

public class SupplierComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		SupplierServices supplierServices = (SupplierServices) inputElement;
		return supplierServices.getSuppliers().toArray();
	}

}
