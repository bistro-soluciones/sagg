package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.model.location.Country;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.RefdataServices;

public class StateComboContentProvider extends SaggStructuredContentProvider {

	private Country country;

	public StateComboContentProvider(Country country) {
		super();
		this.country = country;
	}

	@Override
	public void dispose() {
		this.country = null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		RefdataServices refdataServices = (RefdataServices) inputElement;
		return refdataServices.getStatesByCountry(country).toArray();
	}

}
