package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MARKETABLE_PRODUCT")
public class MarketableProduct extends Product {

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
