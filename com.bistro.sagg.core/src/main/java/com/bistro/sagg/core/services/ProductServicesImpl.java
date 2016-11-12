package com.bistro.sagg.core.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.factory.ProductCategoryFactory;
import com.bistro.sagg.core.factory.ProductFactory;
import com.bistro.sagg.core.factory.RecipeFactory;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingItem;
import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.model.products.ComboItem;
import com.bistro.sagg.core.model.products.InventoryProductCategory;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.ProductFormat;
import com.bistro.sagg.core.model.products.Recipe;
import com.bistro.sagg.core.model.products.RecipeLine;
import com.bistro.sagg.core.model.products.SalableProduct;
import com.bistro.sagg.core.model.products.SaleProductCategory;
import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.repository.ComboRepository;
import com.bistro.sagg.core.repository.InventoryProductCategoryRepository;
import com.bistro.sagg.core.repository.MarketableProductRepository;
import com.bistro.sagg.core.repository.ProductFormatRepository;
import com.bistro.sagg.core.repository.RecipeRepository;
import com.bistro.sagg.core.repository.SaleProductCategoryRepository;
import com.bistro.sagg.core.repository.SupplyRepository;

public class ProductServicesImpl implements ProductServices {

	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private MarketableProductRepository marketableProductRepository;
	@Autowired
	private InventoryProductCategoryRepository inventoryProductCategoryRepository;
	@Autowired
	private SaleProductCategoryRepository saleProductCategoryRepository;
	@Autowired
	private ProductFormatRepository productFormatRepository;
	@Autowired
	private RecipeRepository recipeRepository;
	@Autowired
	private SupplyRepository supplyRepository;

	public void createCategory(FranchiseBranch branch, String name, boolean isForSale) {
		if(isForSale) {
			createSaleProductCategory(branch, name);
		} else {
			createInventoryProductCategory(branch, name);
		}
	}

	private void createSaleProductCategory(FranchiseBranch branch, String name) {
		// Create product category object
		SaleProductCategory category = ProductCategoryFactory.createSaleProductCategory(branch, name);
		// Save product category
		saleProductCategoryRepository.save(category);
	}

	private void createInventoryProductCategory(FranchiseBranch branch, String name) {
		// Create product category object
		InventoryProductCategory category = ProductCategoryFactory.createInventoryProductCategory(branch, name);
		// Save product category
		inventoryProductCategoryRepository.save(category);
	}

	public List<ProductCategory> getProductCategories(FranchiseBranch branch) {
		List<ProductCategory> categories = new ArrayList<ProductCategory>();
		categories.addAll(getSaleProductCategories(branch));
		categories.addAll(getInventoryProductCategories(branch));
		return categories;
	}

	public List<InventoryProductCategory> getInventoryProductCategories(FranchiseBranch branch) {
		return inventoryProductCategoryRepository.findByBranch(branch);
	}

	public List<SaleProductCategory> getSaleProductCategories(FranchiseBranch branch) {
		return saleProductCategoryRepository.findByBranch(branch);
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
			BigDecimal unitSalesPrice, ProductFormat format) {
		// Create marketable product object
		MarketableProduct product = ProductFactory.createMarketableProduct(name, category, 0, minStock, format);
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

	public List<SalableProduct> getSalableProductsByCategory(ProductCategory category) {
		List<SalableProduct> products = new ArrayList<SalableProduct>();
		products.addAll(marketableProductRepository.findByCategory(category));
		products.addAll(recipeRepository.findByCategory(category));
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

	public void increaseProductStock(List<PurchaseBillingItem> items) {
		// Increase product stock
		for (PurchaseBillingItem item : items) {
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
	
	public void decreaseProductStock(SalableProduct product, int quantity) {
		product.decreaseStock(quantity);
		if (product instanceof Combo) {
			// Save combo
			comboRepository.save((Combo) product);
		}
		if (product instanceof MarketableProduct) {
			// Save marketable product
			marketableProductRepository.save((MarketableProduct) product);
		}
		if (product instanceof Recipe) {
			// Save recipe
			recipeRepository.save((Recipe) product);
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
