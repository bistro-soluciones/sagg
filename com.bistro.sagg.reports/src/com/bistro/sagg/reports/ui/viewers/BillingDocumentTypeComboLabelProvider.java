package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class BillingDocumentTypeComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof DocumentType) {
			return ((DocumentType) element).getName();
		}
		return element.toString();
	}

}
