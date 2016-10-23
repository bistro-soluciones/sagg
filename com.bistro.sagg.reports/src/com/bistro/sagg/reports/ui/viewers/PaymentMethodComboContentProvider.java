package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.BillingServices;

public class PaymentMethodComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		BillingServices billingServices = (BillingServices) inputElement;
		return billingServices.getPaymentMethods().toArray();
	}

}
