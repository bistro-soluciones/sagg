package com.bistro.sagg.suppliers.ui.viewers;

import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.suppliers.ui.utils.SupplierColumnIndex;

public class SupplierListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Supplier supplier = (Supplier) element;
		switch (columnIndex) {
		case SupplierColumnIndex.BUSINESS_NAME_COLUMN_IDX:
			return supplier.getBusinessName();
		case SupplierColumnIndex.SUPPLIER_ID_COLUMN_IDX:
			return supplier.getSupplierId();
		case SupplierColumnIndex.SUPPLIES_COLUMN_IDX:
			return "";
		case SupplierColumnIndex.CONTACT_COLUMN_IDX:
			return supplier.getContact().getFullName();
		case SupplierColumnIndex.PHONE_COLUMN_IDX:
			return supplier.getContact().getPhone();
		case SupplierColumnIndex.CELLPHONE_COLUMN_IDX:
			return supplier.getContact().getCellphone();
		case SupplierColumnIndex.EMAIL_COLUMN_IDX:
			return supplier.getContact().getEmail();
		}
		return "";
	}

}
