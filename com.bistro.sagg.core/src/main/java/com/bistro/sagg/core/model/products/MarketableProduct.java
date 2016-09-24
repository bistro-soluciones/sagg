package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MARKETABLE_PRODUCTS")
public class MarketableProduct extends Supply {

	@Column(name = "UNIT_SALES_PRICE")
	private BigDecimal unitSalesPrice;

	public MarketableProduct() {
		super();
	}

	public BigDecimal getUnitSalesPrice() {
		return unitSalesPrice;
	}

	public void setUnitSalesPrice(BigDecimal unitSalesPrice) {
		this.unitSalesPrice = unitSalesPrice;
	}

}
