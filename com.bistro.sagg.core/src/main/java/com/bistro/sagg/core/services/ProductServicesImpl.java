package com.bistro.sagg.core.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.MarketableProductBuilder;
import com.bistro.sagg.core.builders.ProductCategoryBuilder;
import com.bistro.sagg.core.builders.SupplyBuilder;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.repository.MarketableProductRepository;
import com.bistro.sagg.core.repository.ProductCategoryRepository;
import com.bistro.sagg.core.repository.SupplyRepository;

public class ProductServicesImpl implements ProductServices {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	private SupplyRepository supplyRepository;
	@Autowired
	private MarketableProductRepository marketableProductRepository;

	public void createCategory(String name) {
		// Create product category object
		ProductCategoryBuilder builder = new ProductCategoryBuilder();
		builder.build(name);
		ProductCategory category = builder.getCategory();
		// Save product category
		productCategoryRepository.save(category);
	}

	public List<ProductCategory> getProductCategories() {
		return (List<ProductCategory>) productCategoryRepository.findAll();
	}

	public void createSypply(String name, ProductCategory category, int stock, int minStock, BigDecimal unitPrice) {
		// Create supply object
		SupplyBuilder builder = new SupplyBuilder();
		builder.build(name, category, stock, minStock, unitPrice);
		Supply product = builder.getProduct();
		// Save supply
		supplyRepository.save(product);
	}

	public List<Supply> getSupplies() {
		return (List<Supply>) supplyRepository.findAll();
	}

	public void createMarketableProduct(String name, ProductCategory category, int stock, int minStock,
			BigDecimal unitPrice, BigDecimal unitSalesPrice) {
		// Create marketable product object
		MarketableProductBuilder builder = new MarketableProductBuilder();
		builder.build(name, category, stock, minStock, unitPrice, unitSalesPrice);
		MarketableProduct product = builder.getProduct();
		// Save marketable product
		marketableProductRepository.save(product);
	}

	public List<MarketableProduct> getMarketableProducts() {
		return (List<MarketableProduct>) marketableProductRepository.findAll();
	}

}
