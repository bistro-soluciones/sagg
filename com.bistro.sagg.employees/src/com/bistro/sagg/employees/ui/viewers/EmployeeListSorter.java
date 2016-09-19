package com.bistro.sagg.employees.ui.viewers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.employees.ui.utils.EmployeeColumnIndex;

public class EmployeeListSorter extends ViewerSorter {

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
		Employee employee1 = (Employee) e1;
		Employee employee2 = (Employee) e2;

		// Determine which column and do the appropriate sort
		switch (column) {
		case EmployeeColumnIndex.NAME_COLUMN_IDX:
			result = employee1.getFullName().compareTo(employee2.getFullName());
			break;
		case EmployeeColumnIndex.PERSON_ID_COLUMN_IDX:
			result = employee1.getPersonId().compareTo(employee2.getPersonId());
			break;
		case EmployeeColumnIndex.PHONE_COLUMN_IDX:
			result = employee1.getPhone().compareTo(employee2.getPhone());
			break;
		case EmployeeColumnIndex.CELLPHONE_COLUMN_IDX:
			result = employee1.getCellphone().compareTo(employee2.getCellphone());
			break;
		case EmployeeColumnIndex.EMAIL_COLUMN_IDX:
			result = employee1.getEmail().compareTo(employee2.getEmail());
			break;
		}

		// If descending order, flip the direction
		if (direction == DESCENDING) {
			result = -result;
		}

		return result;
	}

}
