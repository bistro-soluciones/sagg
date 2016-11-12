package com.bistro.sagg.core.model.order.billing;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Recipe;

@Entity
@Table(name = "SALE_BILLING_ITEMS")
public class SaleBillingItem extends BillingItem {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private MarketableProduct product;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECIPE_ID")
	private Recipe recipe;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMBO_ID")
	private Combo combo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_DOCUMENT_ID")
	private SaleBillingDocument document;
	
	public SaleBillingItem() {
		super();
	}

	public MarketableProduct getProduct() {
		return product;
	}

	public void setProduct(MarketableProduct product) {
		this.product = product;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public SaleBillingDocument getDocument() {
		return document;
	}

	public void setDocument(SaleBillingDocument document) {
		this.document = document;
	}

	@Override
	public String getProductName() {
		if(getCombo() != null) {
			return getCombo().getName();
		}
		if(getProduct() != null) {
			return getProduct().getName();
		}
		if(getRecipe() != null) {
			return getRecipe().getName();
		}
		return "";
	}

	@Override
	public String getProductCategoryName() {
		if(getCombo() != null) {
			return "Combo";
		}
		if(getProduct() != null) {
			return getProduct().getCategory().getName();
		}
		if(getRecipe() != null) {
			return getRecipe().getCategory().getName();
		}
		return "";
	}

}
