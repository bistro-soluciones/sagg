package com.bistro.sagg.core.model.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMBO_ITEMS")
public class ComboItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMBO_ID")
	private Combo combo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	private MarketableProduct product;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECIPE_ID")
	private Recipe recipe;
	@Column(name = "QUANTITY")
	private int quantity = 0;

	public ComboItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getItemName() {
		String itemName = "";
		if (getProduct() != null) {
			itemName = getProduct().getName();
		}
		if (getRecipe() != null) {
			itemName = getRecipe().getName();
		}
		return itemName;
	}

}
