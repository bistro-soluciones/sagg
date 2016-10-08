package com.bistro.sagg.core.model.order;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "SALE_ORDER_ITEMS")
public class SaleOrderItem extends OrderItem {

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

}
