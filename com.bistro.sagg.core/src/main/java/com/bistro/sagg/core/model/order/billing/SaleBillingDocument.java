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

import com.bistro.sagg.core.model.company.employees.Employee;

@Entity
@Table(name = "SALE_BILLING_DOCUMENTS")
public class SaleBillingDocument extends BillingDocument {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee seller;
	@OneToMany(mappedBy = "document", cascade = CascadeType.PERSIST)
	private List<SaleBillingItem> items = new ArrayList<SaleBillingItem>();

	public SaleBillingDocument() {
		super();
	}

	public Employee getSeller() {
		return seller;
	}

	public void setSeller(Employee seller) {
		this.seller = seller;
	}

	public List<SaleBillingItem> getItems() {
		return items;
	}

	public void setItems(List<SaleBillingItem> items) {
		this.items = items;
	}

	public void addItem(SaleBillingItem item) {
		item.setDocument(this);
		this.items.add(item);
	}

	public void addItems(List<SaleBillingItem> items) {
		for (SaleBillingItem item : items) {
			item.setDocument(this);
		}
		this.items.addAll(items);
	}
}
