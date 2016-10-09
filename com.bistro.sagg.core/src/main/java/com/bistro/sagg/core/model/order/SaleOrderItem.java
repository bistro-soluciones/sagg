package com.bistro.sagg.core.model.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.SalableProduct;

@Entity
@Table(name = "SALE_ORDER_ITEMS")
public class SaleOrderItem extends OrderItem {

	@Column(name = "AMOUNT")
	private BigDecimal amount = BigDecimal.ZERO;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private SaleOrder order;

	public SaleOrderItem() {
		super();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
		this.amount = this.amount.add(getSalableProduct().getUnitSalesPrice().multiply(new BigDecimal(getQuantity())));
	}

}
