package com.bistro.sagg.suppliers.ui.viewers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.suppliers.ui.utils.SupplierColumnIndex;

public class SupplierListSorter extends ViewerSorter {

	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;

	private int column;
	private int direction;

	/**
	 * Does the sort. If it's a different column from the previous sort, do an
	 * ascending sort. If it's the same column as the last sort, toggle the sort
	 * direction.
	 * 
	 * @param column
	 */
	public void doSort(int column) {
		if (column == this.column) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.column = column;
			direction = ASCENDING;
		}
	}

	/**
	 * Compares the object for sorting
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		int result = 0;
		Supplier supplier1 = (Supplier) e1;
		Supplier supplier2 = (Supplier) e2;

		// Determine which column and do the appropriate sort
		switch (column) {
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

		// If descending order, flip the direction
		if (direction == DESCENDING) {
			result = -result;
		}

		return result;
	}

}
