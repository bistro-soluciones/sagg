package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class CityComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof City) {
			return ((City) element).getName();
		}
		return element.toString();
	}

}
