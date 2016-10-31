package com.bistro.sagg.core.builders;

import com.bistro.sagg.core.model.company.FranchiseBranch;
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

	public void build(FranchiseBranch branch, String name) {
		this.category.setBranch(branch);
		this.category.setName(name);
	}

	public ProductCategory getCategory() {
		return category;
	}

}
