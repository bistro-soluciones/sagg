package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class PaymentMethodComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof PaymentMethod) {
			return ((PaymentMethod) element).getName();
		}
		return element.toString();
	}

}