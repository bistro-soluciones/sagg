package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggTableLabelProvider;

public class GenerigReportsLabelProvider extends SaggTableLabelProvider {

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Object[] line = (Object[]) element;
		if(line[columnIndex] != null) {
			return line[columnIndex].toString();
		}
		return "";
	}

}
