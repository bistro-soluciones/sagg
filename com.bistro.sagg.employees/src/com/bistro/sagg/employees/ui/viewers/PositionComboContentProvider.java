package com.bistro.sagg.employees.ui.viewers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bistro.sagg.core.services.RefdataServices;

public class PositionComboContentProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		RefdataServices refdataServices = (RefdataServices) inputElement;
		return refdataServices.getPositions().toArray();
	}

}
