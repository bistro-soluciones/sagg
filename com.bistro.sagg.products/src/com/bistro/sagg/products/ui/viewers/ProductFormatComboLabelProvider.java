package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.products.ProductFormat;
import com.bistro.sagg.core.osgi.ui.viewers.SaggLabelProvider;

public class ProductFormatComboLabelProvider extends SaggLabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof ProductFormat) {
			return ((ProductFormat) element).getName();
		}
		return element.toString();
	}

}
