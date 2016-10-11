package com.bistro.sagg.core.builders;

import com.bistro.sagg.core.model.order.SaleOrder;

public class SaleOrderBuilder {

	private SaleOrder order;

	public SaleOrderBuilder() {
		super();
		this.order = new SaleOrder();
	}

	public SaleOrderBuilder(SaleOrder order) {
		super();
		this.order = order;
	}


	public SaleOrder getOrder() {
		return order;
	}

}
