package com.bistro.sagg.core.model.order.billing;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bistro.sagg.core.model.suppliers.Supplier;

@Entity
@Table(name = "PURCHASE_BILLING_DOCUMENTS")
public class PurchaseBillingDocument extends BillingDocument {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	@OneToMany(mappedBy = "document", cascade = CascadeType.PERSIST)
	private List<PurchaseBillingItem> items = new ArrayList<PurchaseBillingItem>();

	public PurchaseBillingDocument() {
		super();
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<PurchaseBillingItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseBillingItem> items) {
		this.items = items;
	}

	public void addItem(PurchaseBillingItem item) {
		item.setDocument(this);
		this.items.add(item);
	}

	public void addItems(List<PurchaseBillingItem> items) {
		for (PurchaseBillingItem item : items) {
			item.setDocument(this);
		}
		this.items.addAll(items);
	}

}
