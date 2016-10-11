package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;

public interface OrderServices extends ISaggService {

	SaleOrder createSaleOrder(FranchiseBranch branch, Employee seller, List<SaleOrderItem> items);
	
	void cancelSaleOrder(SaleOrder order);
	
	void deliverSaleOrder(SaleOrder order);

}
