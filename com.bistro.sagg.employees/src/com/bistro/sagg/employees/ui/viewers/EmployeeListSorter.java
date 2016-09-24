package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;
import com.bistro.sagg.employees.ui.utils.EmployeeColumnIndex;

public class EmployeeListSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		int result = 0;
		Employee employee1 = (Employee) e1;
		Employee employee2 = (Employee) e2;

		// Determine which column and do the appropriate sort
		switch (getColumn()) {
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

		return result;
	}

}
