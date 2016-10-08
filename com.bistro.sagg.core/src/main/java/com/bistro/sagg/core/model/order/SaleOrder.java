package com.bistro.sagg.core.model.order;

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
@Table(name = "SALE_ORDERS")
public class SaleOrder extends Order {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee seller;
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<SaleOrderItem> items;
	
}
