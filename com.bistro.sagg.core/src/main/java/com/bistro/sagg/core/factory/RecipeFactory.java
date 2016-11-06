package com.bistro.sagg.core.factory;

import java.math.BigDecimal;
import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.model.products.ComboItem;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Recipe;
import com.bistro.sagg.core.model.products.RecipeLine;
import com.bistro.sagg.core.model.products.SalableProduct;
import com.bistro.sagg.core.model.products.Supply;

public class RecipeFactory {

	public static Recipe createRecipe(String name, String description, ProductCategory category, List<RecipeLine> lines,
			BigDecimal salesPrice, FranchiseBranch branch) {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setDescription(description);
		recipe.setCategory(category);
		recipe.setUnitSalesPrice(salesPrice);
		for (RecipeLine recipeLine : lines) {
			recipe.addLine(recipeLine);
		}
		recipe.setBranch(branch);
		return recipe;
	}

	public static RecipeLine createRecipeLine(Supply supply, int portion) {
		RecipeLine line = new RecipeLine();
		line.setSupply(supply);
		line.setPortion(portion);
		return line;
	}
	
	public static Combo createCombo(String name, String description, List<ComboItem> items,
			BigDecimal salesPrice, FranchiseBranch branch) {
		Combo combo = new Combo();
		combo.setName(name);
		combo.setDescription(description);
		combo.setUnitSalesPrice(salesPrice);
		for (ComboItem item : items) {
			combo.addItem(item);
		}
		combo.setBranch(branch);
		return combo;
	}

	public static ComboItem createComboItem(SalableProduct product, int quantity) {
		ComboItem item = new ComboItem();
		item.setQuantity(quantity);
		product.addToComboItem(item);
		return item;
	}

}
