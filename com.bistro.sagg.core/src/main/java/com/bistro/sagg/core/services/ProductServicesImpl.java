package com.bistro.sagg.core.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.MarketableProductBuilder;
import com.bistro.sagg.core.builders.ProductCategoryBuilder;
import com.bistro.sagg.core.factory.ProductFactory;
import com.bistro.sagg.core.factory.RecipeFactory;
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
import com.bistro.sagg.core.repository.ComboRepository;
import com.bistro.sagg.core.repository.MarketableProductRepository;
import com.bistro.sagg.core.repository.ProductCategoryRepository;
import com.bistro.sagg.core.repository.ProductFormatRepository;
import com.bistro.sagg.core.repository.RecipeRepository;
import com.bistro.sagg.core.repository.SupplyRepository;

public class ProductServicesImpl implements ProductServices {

	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private MarketableProductRepository marketableProductRepository;
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Autowired
	private ProductFormatRepository productFormatRepository;
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private SupplyRepository supplyRepository;

	public void createCategory(FranchiseBranch branch, String name) {
		// Create product category object
		ProductCategoryBuilder builder = new ProductCategoryBuilder();
		builder.build(branch, name);
		ProductCategory category = builder.getCategory();
		// Save product category
		productCategoryRepository.save(category);
	}

	public List<ProductCategory> getProductCategories(FranchiseBranch branch) {
		return productCategoryRepository.findByBranch(branch);
	}

	public List<ProductFormat> getProductFormats() {
		return (List<ProductFormat>) productFormatRepository.findAll();
	}

	public void createSupply(String name, ProductCategory category, int minStock, ProductFormat format) {
		// Create supply object
		Supply supply = ProductFactory.createSupply(name, category, 0, minStock, format);
		// Save supply
		supplyRepository.save(supply);
	}

	public List<Supply> getSupplies(FranchiseBranch branch) {
		// FIXME get by branch
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

	public List<MarketableProduct> getMarketableProducts(FranchiseBranch branch) {
		// FIXME get by branch
		return (List<MarketableProduct>) marketableProductRepository.findAll();
	}

	public List<Product> getProductsByCategory(ProductCategory category) {
		List<Product> products = new ArrayList<Product>();
		products = addSuppliers(products, category);
		products = addMarketableProducts(products, category);
		return products;
	}

	public List<Supply> getSuppliesByCategory(ProductCategory category) {
		return supplyRepository.findByCategory(category);
	}

	private List<Product> addSuppliers(List<Product> products, ProductCategory category) {
		products.addAll(supplyRepository.findByCategory(category));
		return products;
	}

	private List<Product> addMarketableProducts(List<Product> products, ProductCategory category) {
		products.addAll(marketableProductRepository.findByCategory(category));
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

	public void createRecipe(String name, String description, ProductCategory category, List<RecipeLine> lines,
			BigDecimal salesPrice, FranchiseBranch branch) {
		// Create recipe object
		Recipe recipe = RecipeFactory.createRecipe(name, description, category, lines, salesPrice, branch);
		// Save recipe
		recipeRepository.save(recipe);
	}

	public List<Recipe> getRecipes(FranchiseBranch branch) {
		return recipeRepository.findByBranch(branch);
	}

	public void createCombo(String name, String description, List<ComboItem> items, BigDecimal salesPrice,
			FranchiseBranch branch) {
		// Create combo object
		Combo combo = RecipeFactory.createCombo(name, description, items, salesPrice, branch);
		// Save combo
		comboRepository.save(combo);
	}

	public List<Combo> getCombos(FranchiseBranch branch) {
		return comboRepository.findByBranch(branch);
	}

}
