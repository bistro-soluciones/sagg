package com.bistro.sagg.core.model.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SALE_CATEGORY")
public class SaleProductCategory extends ProductCategory {

	@Override
	public String getType() {
		return "Venta";
	}

}
