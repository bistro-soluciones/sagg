package com.bistro.sagg.core.services;

import java.math.BigDecimal;
import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.model.products.ComboItem;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.ProductFormat;
import com.bistro.sagg.core.model.products.Recipe;
import com.bistro.sagg.core.model.products.RecipeLine;
import com.bistro.sagg.core.model.products.Supply;

public interface ProductServices extends ISaggService {

	void createCategory(FranchiseBranch branch, String name);

	List<ProductCategory> getProductCategories(FranchiseBranch branch);

	List<ProductFormat> getProductFormats();

	void createSupply(String name, ProductCategory category, int minStock, ProductFormat format);

	List<Supply> getSupplies(FranchiseBranch branch);

	void createMarketableProduct(String name, ProductCategory category, int minStock, BigDecimal unitSalesPrice);

	List<MarketableProduct> getMarketableProducts(FranchiseBranch branch);

	List<Product> getProductsByCategory(ProductCategory category);

	List<Supply> getSuppliesByCategory(ProductCategory category);

	void increaseProductStock(List<BillingItem> items);

	void createRecipe(String name, String description, ProductCategory category, List<RecipeLine> lines,
			BigDecimal salesPrice, FranchiseBranch branch);

	List<Recipe> getRecipes(FranchiseBranch branch);

	void createCombo(String name, String description, List<ComboItem> items, BigDecimal salesPrice,
			FranchiseBranch branch);

	List<Combo> getCombos(FranchiseBranch branch);

}
