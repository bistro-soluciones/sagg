package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.EmployeeServices;

public class EmployeeListContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		EmployeeServices employeeServices = (EmployeeServices) inputElement;
		return employeeServices.getEmployees().toArray();
	}

}
