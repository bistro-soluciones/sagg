package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.employees.ui.utils.EmployeeColumnIndex;

public class EmployeeListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Employee employee = (Employee) element;
		switch (columnIndex) {
		case EmployeeColumnIndex.NAME_COLUMN_IDX:
			return employee.getFullName();
		case EmployeeColumnIndex.PERSON_ID_COLUMN_IDX:
			return employee.getPersonId();
		case EmployeeColumnIndex.PHONE_COLUMN_IDX:
			return employee.getPhone();
		case EmployeeColumnIndex.CELLPHONE_COLUMN_IDX:
			return employee.getCellphone();
		case EmployeeColumnIndex.EMAIL_COLUMN_IDX:
			return employee.getEmail();
		}
		return "";
	}

}
