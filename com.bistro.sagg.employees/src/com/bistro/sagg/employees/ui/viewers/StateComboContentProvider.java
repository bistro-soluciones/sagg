package com.bistro.sagg.employees.ui.viewers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bistro.sagg.core.model.location.Country;
import com.bistro.sagg.core.services.RefdataServices;

public class StateComboContentProvider implements IStructuredContentProvider {

	private Country country;

	public StateComboContentProvider(Country country) {
		super();
		this.country = country;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		RefdataServices refdataServices = (RefdataServices) inputElement;
		return refdataServices.getStatesByCountry(country).toArray();
	}

}
