package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.suppliers.Supplier;

public interface OrderServices extends ISaggService {

	SaleOrder createSaleOrder(FranchiseBranch branch, Employee seller, List<SaleOrderItem> items);

	void cancelSaleOrder(SaleOrder order);

	void deliverSaleOrder(SaleOrder order);

	PurchaseOrder createPurchaseOrder(FranchiseBranch branch, Supplier supplier, Employee receiver, List<PurchaseOrderItem> items);

	void cancelPurchaseOrder(PurchaseOrder order);

	void receivePurchaseOrder(PurchaseOrder order);

}
