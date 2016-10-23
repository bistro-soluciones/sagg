package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class EmployeeComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Employee) {
			return ((Employee) element).getFullName();
		}
		return element.toString();
	}

}
