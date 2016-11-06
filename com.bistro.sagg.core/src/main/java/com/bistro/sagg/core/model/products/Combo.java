package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bistro.sagg.core.model.company.FranchiseBranch;

@Entity
@Table(name = "COMBOS")
public class Combo implements SalableProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "UNIT_SALES_PRICE")
	private BigDecimal unitSalesPrice;
	@OneToMany(mappedBy = "combo")
	private List<ComboItem> items = new ArrayList<ComboItem>();
	// Franchise information
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRANCHISE_BRANCH_ID")
	private FranchiseBranch branch;

	public Combo() {
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

	public BigDecimal getUnitSalesPrice() {
		return unitSalesPrice;
	}

	public void setUnitSalesPrice(BigDecimal unitSalesPrice) {
		this.unitSalesPrice = unitSalesPrice;
	}

	public List<ComboItem> getItems() {
		return items;
	}

	public void setItems(List<ComboItem> items) {
		this.items = items;
	}

	public FranchiseBranch getBranch() {
		return branch;
	}

	public void setBranch(FranchiseBranch branch) {
		this.branch = branch;
	}

	public void addItem(ComboItem item) {
		getItems().add(item);
		item.setCombo(this);
	}

	public void addToComboItem(ComboItem item) {
		// Do nothing as combo cannot be part of a combo item
		// TODO throw exception
	}

}
