package com.bistro.sagg.core.builders;

import com.bistro.sagg.core.model.products.ProductCategory;

public class ProductCategoryBuilder {

	private ProductCategory category;

	public ProductCategoryBuilder() {
		super();
		this.category = new ProductCategory();
	}

	public ProductCategoryBuilder(ProductCategory category) {
		super();
		this.category = category;
	}

	public void build(String name) {
		this.category.setName(name);
	}

	public ProductCategory getCategory() {
		return category;
	}

}
