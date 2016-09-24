package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.model.location.State;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class StateComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof State) {
			return ((State) element).getName();
		}
		return element.toString();
	}

}
