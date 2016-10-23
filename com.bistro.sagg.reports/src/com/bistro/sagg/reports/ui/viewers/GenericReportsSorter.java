package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggViewerSorter;

public class GenericReportsSorter extends SaggViewerSorter {

	@Override
	protected int compare(Object e1, Object e2) {
		Object[] line1 = (Object[]) e1;
		Object[] line2 = (Object[]) e2;
		// Determine which column and do the appropriate sort
		return line1[getColumn()].toString().compareTo(line2[getColumn()].toString());
	}

}
