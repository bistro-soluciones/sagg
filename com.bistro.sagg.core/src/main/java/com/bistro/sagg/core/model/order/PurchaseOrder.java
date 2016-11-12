package com.bistro.sagg.core.model.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingDocument;
import com.bistro.sagg.core.model.suppliers.Supplier;

@Entity
@Table(name = "PURCHASE_ORDERS")
public class PurchaseOrder extends Order {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REQUESTOR_ID")
	private Employee requestor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVER_ID")
	private Employee receiver;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<PurchaseOrderItem> items;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_DOCUMENT_ID")
	private PurchaseBillingDocument document;
	
	public PurchaseOrder() {
		super();
	}

	public Employee getRequestor() {
		return requestor;
	}

	public void setRequestor(Employee requestor) {
		this.requestor = requestor;
	}

	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<PurchaseOrderItem> getItems() {
		return items;
	}

	public void setItems(List<PurchaseOrderItem> items) {
		this.items = items;
	}

	public PurchaseBillingDocument getDocument() {
		return document;
	}

	public void setDocument(PurchaseBillingDocument document) {
		this.document = document;
	}

}
