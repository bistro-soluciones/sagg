package com.bistro.sagg.suppliers.ui.viewers;

import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.suppliers.ui.utils.SupplierColumnIndex;

public class SupplierListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		Supplier supplier1 = (Supplier) e1;
		Supplier supplier2 = (Supplier) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
		case SupplierColumnIndex.BUSINESS_NAME_COLUMN_IDX:
			result = supplier1.getBusinessName().compareTo(supplier2.getBusinessName());
			break;
		case SupplierColumnIndex.SUPPLIER_ID_COLUMN_IDX:
			result = supplier1.getSupplierId().compareTo(supplier2.getSupplierId());
			break;
		case SupplierColumnIndex.CONTACT_COLUMN_IDX:
			result = supplier1.getContact().getFullName().compareTo(supplier2.getContact().getFullName());
			break;
		case SupplierColumnIndex.PHONE_COLUMN_IDX:
			result = supplier1.getContact().getPhone().compareTo(supplier2.getContact().getPhone());
			break;
		case SupplierColumnIndex.CELLPHONE_COLUMN_IDX:
			result = supplier1.getContact().getCellphone().compareTo(supplier2.getContact().getCellphone());
			break;
		case SupplierColumnIndex.EMAIL_COLUMN_IDX:
			result = supplier1.getContact().getEmail().compareTo(supplier2.getContact().getEmail());
			break;
		}

		return result;
	}

}
