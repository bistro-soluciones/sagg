package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.RefdataServices;

public class PositionComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		RefdataServices refdataServices = (RefdataServices) inputElement;
		return refdataServices.getPositions().toArray();
	}

}
