package com.bistro.sagg.core.model.order;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.Combo;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Recipe;
import com.bistro.sagg.core.model.products.SalableProduct;

@Entity
@Table(name = "SALE_ORDER_ITEMS")
public class SaleOrderItem extends OrderItem {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private MarketableProduct product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECIPE_ID")
	private Recipe recipe;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMBO_ID")
	private Combo combo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID")
	private SaleOrder order;

	public SaleOrderItem() {
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

	public SaleOrder getOrder() {
		return order;
	}

	public void setOrder(SaleOrder order) {
		this.order = order;
	}

	public SalableProduct getSalableProduct() {
		if (getCombo() != null) {
			return getCombo();
		}
		if (getProduct() != null) {
			return getProduct();
		}
		return getRecipe();
	}

	public void setSalableProduct(SalableProduct product) {
		product.addToSaleOrderItem(this);
	}

	public void recalculateAmount() {
		setAmount(getSalableProduct().getUnitSalesPrice().multiply(new BigDecimal(getQuantity())));
	}

}
