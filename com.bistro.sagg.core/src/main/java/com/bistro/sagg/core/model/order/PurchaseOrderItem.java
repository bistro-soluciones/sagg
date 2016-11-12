package com.bistro.sagg.core.model.order;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.Product;

@Entity
@Table(name = "PURCHASE_ORDER_ITEMS")
public class PurchaseOrderItem extends OrderItem {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	@Column(name = "PURCHASE_UNIT_PRICE")
	private BigDecimal purchaseUnitPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private PurchaseOrder order;
	@Column(name = "AVAILABLE_STOCK")
	private int availableStock = 0;

	public PurchaseOrderItem() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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
	
	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public void recalculateAmount() {
		setAmount(getPurchaseUnitPrice().multiply(new BigDecimal(getQuantity())));
	}

	public void decreaseAvailableStock(int quantity) {
		setAvailableStock(getAvailableStock() - quantity);
	}

}
