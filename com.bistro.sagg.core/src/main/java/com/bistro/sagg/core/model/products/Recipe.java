package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.company.FranchiseBranch;

@Entity
@Table(name = "RECIPES")
public class Recipe implements SalableProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "PRODUCT_CATEGORY_ID")
	private ProductCategory category;
	@Column(name = "UNIT_SALES_PRICE")
	private BigDecimal unitSalesPrice;
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
	private List<RecipeLine> lines = new ArrayList<RecipeLine>();
	// Franchise information
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRANCHISE_BRANCH_ID")
	private FranchiseBranch branch;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMBO_ID")
	private Combo combo;

	public Recipe() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public BigDecimal getUnitSalesPrice() {
		return unitSalesPrice;
	}

	public void setUnitSalesPrice(BigDecimal unitSalesPrice) {
		this.unitSalesPrice = unitSalesPrice;
	}

	public List<RecipeLine> getLines() {
		return lines;
	}

	public void setLines(List<RecipeLine> lines) {
		this.lines = lines;
	}
	
	public FranchiseBranch getBranch() {
		return branch;
	}

	public void setBranch(FranchiseBranch branch) {
		this.branch = branch;
	}

	public void addLine(RecipeLine recipeLine) {
		getLines().add(recipeLine);
		recipeLine.setRecipe(this);
	}

	public void addToComboItem(ComboItem item) {
		item.setRecipe(this);
	}

}
