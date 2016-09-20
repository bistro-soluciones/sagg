package com.bistro.sagg.suppliers.ui.viewers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bistro.sagg.core.services.SupplierServices;

public class SupplierListContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		SupplierServices supplierServices = (SupplierServices) inputElement;
		return supplierServices.getSuppliers().toArray();
	}

}
