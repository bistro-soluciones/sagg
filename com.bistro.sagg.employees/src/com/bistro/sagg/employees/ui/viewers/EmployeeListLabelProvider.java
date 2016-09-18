package com.bistro.sagg.employees.ui.viewers;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.bistro.sagg.core.model.Employee;
import com.bistro.sagg.employees.ui.utils.EmployeeColumnIndex;

public class EmployeeListLabelProvider implements ITableLabelProvider {

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
		Employee employee = (Employee) element;
		switch(columnIndex) {
			case EmployeeColumnIndex.NAME_COLUMN_IDX: return employee.getFullName();
			case EmployeeColumnIndex.PERSON_ID_COLUMN_IDX: return employee.getPersonId();
			case EmployeeColumnIndex.PHONE_COLUMN_IDX: return employee.getPhone();
			case EmployeeColumnIndex.CELLPHONE_COLUMN_IDX: return employee.getCellphone();
			case EmployeeColumnIndex.EMAIL_COLUMN_IDX: return employee.getEmail();
		}
		return "";
	}

}
