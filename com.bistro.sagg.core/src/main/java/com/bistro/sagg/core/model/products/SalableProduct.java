package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;

public interface SalableProduct {

	BigDecimal getUnitSalesPrice();
	
	void addToComboItem(ComboItem item);

}
