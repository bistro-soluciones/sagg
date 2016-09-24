package com.bistro.sagg.employees.ui.viewers;

import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class PositionComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof Position) {
			return ((Position) element).getPosition();
		}
		return element.toString();
	}

}
