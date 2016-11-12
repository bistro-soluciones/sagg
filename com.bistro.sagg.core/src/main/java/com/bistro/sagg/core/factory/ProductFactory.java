package com.bistro.sagg.core.factory;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.ProductFormat;
import com.bistro.sagg.core.model.products.Supply;

public class ProductFactory {

	private ProductFactory() {
		super();
	}

	public static MarketableProduct createMarketableProduct(String name, ProductCategory category, int stock, int minStock, ProductFormat format) {
		MarketableProduct product = new MarketableProduct();
		product.setName(name);
		product.setCategory(category);
		product.setStock(stock * format.getUnit());
		product.setMinStock(minStock);
		product.setFormat(format);
		return product;
	}
	

	public static Supply createSupply(String name, ProductCategory category, int stock, int minStock, ProductFormat format) {
		Supply supply = new Supply();
		supply.setName(name);
		supply.setCategory(category);
		supply.setStock(stock * format.getUnit());
		supply.setMinStock(minStock);
		supply.setFormat(format);
		return supply;
	}

}
