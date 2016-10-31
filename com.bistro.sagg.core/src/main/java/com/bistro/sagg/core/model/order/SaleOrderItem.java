package com.bistro.sagg.core.model.order;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.SalableProduct;

@Entity
@Table(name = "SALE_ORDER_ITEMS")
public class SaleOrderItem extends OrderItem {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID")
	private SaleOrder order;

	public SaleOrderItem() {
		super();
	}

	public SaleOrder getOrder() {
		return order;
	}

	public void setOrder(SaleOrder order) {
		this.order = order;
	}

	public SalableProduct getSalableProduct() {
		return (SalableProduct) getProduct();
	}

	public void recalculateAmount() {
		setAmount(getSalableProduct().getUnitSalesPrice().multiply(new BigDecimal(getQuantity())));
	}

}
