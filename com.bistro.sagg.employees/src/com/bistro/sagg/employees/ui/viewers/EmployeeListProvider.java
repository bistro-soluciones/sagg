package com.bistro.sagg.employees.ui.viewers;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.SaggServiceLocator;

public class EmployeeListProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		EmployeeServices employeeServices = (EmployeeServices) inputElement;
		return employeeServices.getEmployees().toArray();
	}

}
