package com.bistro.sagg.core.factory;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.OrderStatusConstants;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.suppliers.Supplier;

public class OrderFactory {

	public static SaleOrder createSaleOrder(FranchiseBranch branch, Employee seller, String orderNumber,
			List<SaleOrderItem> items) {
		SaleOrder order = new SaleOrder();
		order.setOrderNumber(orderNumber);
		order.setBranch(branch);
		order.setDate(Date.from(Instant.now()));
		order.setStatus(OrderStatusConstants.READY);
		order.setSeller(seller);
		order.setItems(items);
		for (SaleOrderItem item : items) {
			item.setOrder(order);
		}
		return order;
	}

	public static PurchaseOrder createPurchaseOrder(FranchiseBranch branch, Supplier supplier, Employee receiver,
			String orderNumber, List<PurchaseOrderItem> items) {
		PurchaseOrder order = new PurchaseOrder();
		order.setOrderNumber(orderNumber);
		order.setBranch(branch);
		order.setDate(Date.from(Instant.now()));
		order.setStatus(OrderStatusConstants.READY);
		order.setSupplier(supplier);
		order.setRequestor(receiver); // FIXME
		order.setReceiver(receiver);
		order.setItems(items);
		for (PurchaseOrderItem item : items) {
			item.setOrder(order);
		}
		return order;
	}

}