package com.bistro.sagg.core.model.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PURCHASE_ORDER_ITEMS")
public class PurchaseOrderItem extends OrderItem {

	@Column(name = "PURCHASE_UNIT_PRICE")
	private BigDecimal purchaseUnitPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private PurchaseOrder order;

	public PurchaseOrderItem() {
		super();
	}

	public BigDecimal getPurchaseUnitPrice() {
		return purchaseUnitPrice;
	}

	public void setPurchaseUnitPrice(BigDecimal purchaseUnitPrice) {
		this.purchaseUnitPrice = purchaseUnitPrice;
	}

	public PurchaseOrder getOrder() {
		return order;
	}

	public void setOrder(PurchaseOrder order) {
		this.order = order;
	}

}
