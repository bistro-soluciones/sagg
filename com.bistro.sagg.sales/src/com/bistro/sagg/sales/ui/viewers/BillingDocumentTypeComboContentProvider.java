package com.bistro.sagg.sales.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.BillingServices;

public class BillingDocumentTypeComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		BillingServices billingServices = (BillingServices) inputElement;
		return billingServices.getBillingDocumentTypes().toArray();
	}

}
