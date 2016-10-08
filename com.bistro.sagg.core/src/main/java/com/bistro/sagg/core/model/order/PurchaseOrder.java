package com.bistro.sagg.core.model.order;

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
@Table(name = "PURCHASE_ORDERS")
public class PurchaseOrder extends Order {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<PurchaseOrderItem> items;
}
