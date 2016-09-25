package com.bistro.sagg.core.services;

import java.math.BigDecimal;
import java.util.List;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Supply;

public interface ProductServices extends ISaggService {

	void createCategory(String name);

	List<ProductCategory> getProductCategories();

	void createSypply(String name, ProductCategory category, int minStock);

	List<Supply> getSupplies();

	void createMarketableProduct(String name, ProductCategory category, int minStock, BigDecimal unitSalesPrice);

	List<MarketableProduct> getMarketableProducts();

}
