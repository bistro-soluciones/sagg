package com.bistro.sagg.core.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.MarketableProductBuilder;
import com.bistro.sagg.core.builders.ProductCategoryBuilder;
import com.bistro.sagg.core.builders.SupplyBuilder;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.model.suppliers.Supplier;
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

	public List<ProductCategory> getProductCategories(FranchiseBranch branch) {
		return (List<ProductCategory>) productCategoryRepository.findByBranch(branch);
	}

	public void createSupply(String name, ProductCategory category, int minStock) {
		// Create supply object
		SupplyBuilder builder = new SupplyBuilder();
		builder.build(name, category, 0, minStock);
		Supply product = builder.getProduct();
		// Save supply
		supplyRepository.save(product);
	}

	public List<Supply> getSupplies() {
		return (List<Supply>) supplyRepository.findAll();
	}

	public void createMarketableProduct(String name, ProductCategory category, int minStock,
			BigDecimal unitSalesPrice) {
		// Create marketable product object
		MarketableProductBuilder builder = new MarketableProductBuilder();
		builder.build(name, category, 0, minStock, unitSalesPrice);
		MarketableProduct product = builder.getProduct();
		// Save marketable product
		marketableProductRepository.save(product);
	}

	public List<MarketableProduct> getMarketableProducts() {
		return (List<MarketableProduct>) marketableProductRepository.findAll();
	}

	public List<Product> getProductsByCategory(ProductCategory category) {
		List<Product> products = new ArrayList<Product>();
		products = addSuppliers(products, category);
		products = addMarketableProducts(products, category);
		return products;
	}

	private List<Product> addSuppliers(List<Product> products, ProductCategory category) {
		products.addAll(supplyRepository.findAllByCategory(category.getId()));
		return products;
	}

	private List<Product> addMarketableProducts(List<Product> products, ProductCategory category) {
		products.addAll(marketableProductRepository.findAllByCategory(category.getId()));
		return products;
	}

	public void increaseProductStock(List<BillingItem> items) {
		// Increase product stock
		for (BillingItem item : items) {
			Product product = item.getProduct();
			product.addStock(item.getQuantity());
			if (product instanceof Supply) {
				// Save supply
				supplyRepository.save((Supply) product);
			}
			if (product instanceof MarketableProduct) {
				// Save marketable product
				marketableProductRepository.save((MarketableProduct) product);
			}
		}
	}

}
