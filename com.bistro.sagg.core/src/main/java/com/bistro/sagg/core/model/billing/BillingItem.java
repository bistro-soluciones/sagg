package com.bistro.sagg.core.model.billing;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.Product;

@Entity
@Table(name = "BILLING_ITEMS")
public class BillingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	@Column(name = "QUANTITY")
	private int quantity;
	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;
	@Column(name = "TAX")
	private float tax;
	@Column(name = "TAX_AMOUNT")
	private BigDecimal taxAmount;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_DOCUMENT_ID")
	private BillingDocument billingDocument;

	public BillingItem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public BillingDocument getBillingDocument() {
		return billingDocument;
	}

	public void setBillingDocument(BillingDocument billingDocument) {
		this.billingDocument = billingDocument;
	}

}
