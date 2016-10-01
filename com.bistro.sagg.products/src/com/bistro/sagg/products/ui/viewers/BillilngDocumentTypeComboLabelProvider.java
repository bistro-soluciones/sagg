package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.billing.DocumentType;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class BillilngDocumentTypeComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof DocumentType) {
			return ((DocumentType) element).getName();
		}
		return element.toString();
	}

}
