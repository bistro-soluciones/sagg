package com.bistro.sagg.core.factory;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.ProductFormat;
import com.bistro.sagg.core.model.products.Supply;

public class ProductFactory {

	public static Supply createSupply(String name, ProductCategory category, int stock, int minStock, ProductFormat format) {
		Supply supply = new Supply();
		supply.setName(name);
		supply.setCategory(category);
		supply.setStock(stock);
		supply.setMinStock(minStock);
		supply.setFormat(format);
		return supply;
	}

}
