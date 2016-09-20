package com.bistro.sagg.suppliers.ui.viewers;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bistro.sagg.core.model.Supplier;
import com.bistro.sagg.suppliers.ui.utils.SupplierColumnIndex;

public class SupplierListLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Supplier supplier = (Supplier) element;
		switch(columnIndex) {
			case SupplierColumnIndex.NAME_COLUMN_IDX: return supplier.setBusinessName();
			case SupplierColumnIndex.SUPPLIER_ID_COLUMN_IDX: return supplier.getSupplierId();
			case SupplierColumnIndex.SUPPLIES_COLUMN_IDX: return "";
			case SupplierColumnIndex.CONTACT_COLUMN_IDX: return supplier.getContact().getFullName();
			case SupplierColumnIndex.PHONE_COLUMN_IDX: return supplier.getContact().getPhone();
			case SupplierColumnIndex.CELLPHONE_COLUMN_IDX: return supplier.getContact().getCellphone();
			case SupplierColumnIndex.EMAIL_COLUMN_IDX: return supplier.getContact().getEmail();
		}
		return "";
	}

}
