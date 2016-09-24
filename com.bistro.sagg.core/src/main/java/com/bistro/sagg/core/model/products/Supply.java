package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIES")
public class Supply extends Product {

	@Column(name = "STOCK")
	private int stock;
	@Column(name = "STOCK_MIN")
	private int minStock;
	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
