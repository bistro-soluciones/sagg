package com.bistro.sagg.core.builders;

import java.math.BigDecimal;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.ProductCategory;

public class MarketableProductBuilder {

	private MarketableProduct product;

	public MarketableProductBuilder() {
		super();
		this.product = new MarketableProduct();
	}

	public MarketableProductBuilder(MarketableProduct product) {
		super();
		this.product = product;
	}

	public void build(String name, ProductCategory category, int stock, int minStock, BigDecimal unitSalesPrice) {
		this.product.setName(name);
		this.product.setCategory(category);
		this.product.setStock(stock);
		this.product.setMinStock(minStock);
		this.product.setUnitSalesPrice(unitSalesPrice);
	}

	public MarketableProduct getProduct() {
		return product;
	}

}
