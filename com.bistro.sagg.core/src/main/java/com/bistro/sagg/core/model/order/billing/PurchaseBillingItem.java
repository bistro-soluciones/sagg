package com.bistro.sagg.core.model.order.billing;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.products.Product;

@Entity
@Table(name = "PURCHASE_BILLING_ITEMS")
public class PurchaseBillingItem extends BillingItem {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_DOCUMENT_ID")
	private PurchaseBillingDocument document;
	
	public PurchaseBillingItem() {
		super();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PurchaseBillingDocument getDocument() {
		return document;
	}

	public void setDocument(PurchaseBillingDocument document) {
		this.document = document;
	}

	@Override
	public String getProductName() {
		return getProduct().getName();
	}

	@Override
	public String getProductCategoryName() {
		return getProduct().getCategory().getName();
	}

}
