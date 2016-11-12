package com.bistro.sagg.core.model.products;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.Identificable;

@Entity
@Table(name = "RECIPE_LINES")
public class RecipeLine implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "SUPPLY_ID")
	private Supply supply;
	@Column(name = "PORTION")
	private int portion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RECIPE_ID")
	private Recipe recipe;
	
	public RecipeLine() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Supply getSupply() {
		return supply;
	}
	
	public void setSupply(Supply supply) {
		this.supply = supply;
	}
	
	public int getPortion() {
		return portion;
	}
	
	public void setPortion(int portion) {
		this.portion = portion;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public void decreaseStock() {
		getSupply().decreaseStock(getPortion());
	}
	
}
