package com.bistro.sagg.employees.ui.viewers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bistro.sagg.core.services.EmployeeServices;

public class EmployeeListContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		EmployeeServices employeeServices = (EmployeeServices) inputElement;
		return employeeServices.getEmployees().toArray();
	}

}
