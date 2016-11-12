package com.bistro.sagg.core.factory;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.InventoryProductCategory;
import com.bistro.sagg.core.model.products.SaleProductCategory;

public class ProductCategoryFactory {

	private ProductCategoryFactory() {
		super();
	}

	public static InventoryProductCategory createInventoryProductCategory(FranchiseBranch branch, String name) {
		InventoryProductCategory category = new InventoryProductCategory();
		category.setBranch(branch);
		category.setName(name);
		return category;
	}

	public static SaleProductCategory createSaleProductCategory(FranchiseBranch branch, String name) {
		SaleProductCategory category = new SaleProductCategory();
		category.setBranch(branch);
		category.setName(name);
		return category;
	}

}
