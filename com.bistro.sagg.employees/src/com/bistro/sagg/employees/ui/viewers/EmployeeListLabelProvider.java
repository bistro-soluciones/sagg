package com.bistro.sagg.employees.ui.viewers;

import java.text.SimpleDateFormat;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.employees.ui.utils.EmployeeColumnIndex;

public class EmployeeListLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Employee employee = (Employee) element;
		SimpleDateFormat formatter = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.SHORT_DATE_FORMATTER);
		switch (columnIndex) {
		case EmployeeColumnIndex.NAME_COLUMN_IDX:
			return employee.getFullName();
		case EmployeeColumnIndex.PERSON_ID_COLUMN_IDX:
			return employee.getPersonId();
		case EmployeeColumnIndex.POSITION_COLUMN_IDX:
			return employee.getPosition().getPosition();
		case EmployeeColumnIndex.ADDRESS_COLUMN_IDX:
			return employee.getAddressL1() + " " + employee.getAddressL2();
		case EmployeeColumnIndex.PHONE_COLUMN_IDX:
			return employee.getPhone();
		case EmployeeColumnIndex.CELLPHONE_COLUMN_IDX:
			return employee.getCellphone();
		case EmployeeColumnIndex.EMAIL_COLUMN_IDX:
			return employee.getEmail();
		case EmployeeColumnIndex.START_DATE_COLUMN_IDX:
			return formatter.format(employee.getStartDate());
		}
		return "";
	}

}
