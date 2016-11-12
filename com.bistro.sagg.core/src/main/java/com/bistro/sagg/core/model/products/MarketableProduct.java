package com.bistro.sagg.core.model.products;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.order.billing.SaleBillingItem;

@Entity
@DiscriminatorValue("MARKETABLE_PRODUCT")
public class MarketableProduct extends Product implements SalableProduct {

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
	
	public void addToSaleOrderItem(SaleOrderItem item) {
		item.setProduct(this);
	}

	public void addToComboItem(ComboItem item) {
		item.setProduct(this);
	}

	public void addToSaleBillingItem(SaleBillingItem item) {
		item.setProduct(this);
	}

	public void decreaseStock(int quantity) {
		setStock(getStock() - quantity);
	}

}
