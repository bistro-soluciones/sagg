package com.bistro.sagg.core.builders;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Supply;

public class SupplyBuilder {

	private Supply product;

	public SupplyBuilder() {
		super();
		this.product = new Supply();
	}

	public SupplyBuilder(Supply product) {
		super();
		this.product = product;
	}

	public void build(String name, ProductCategory category, int stock, int minStock, BigDecimal unitPrice) {
		this.product.setName(name);
		this.product.setCategory(category);
		this.product.setStock(stock);
		this.product.setMinStock(minStock);
		this.product.setUnitPrice(unitPrice);
	}

	public Supply getProduct() {
		return product;
	}

}
