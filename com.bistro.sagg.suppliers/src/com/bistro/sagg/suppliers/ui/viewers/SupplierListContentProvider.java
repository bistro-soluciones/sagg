package com.bistro.sagg.suppliers.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.SupplierServices;

public class SupplierListContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		SupplierServices supplierServices = (SupplierServices) inputElement;
		return supplierServices.getSuppliers().toArray();
	}

}
