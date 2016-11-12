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
import com.bistro.sagg.core.model.order.billing.SaleBillingDocument;

@Entity
@Table(name = "SALE_ORDERS")
public class SaleOrder extends Order {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee seller;
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<SaleOrderItem> items;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BILLING_DOCUMENT_ID")
	private SaleBillingDocument document;
	
	public SaleOrder() {
		super();
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public List<SaleOrderItem> getItems() {
		return items;
	}

	public void setItems(List<SaleOrderItem> items) {
		this.items = items;
	}

	public SaleBillingDocument getDocument() {
		return document;
	}

	public void setDocument(SaleBillingDocument document) {
		this.document = document;
	}

}
