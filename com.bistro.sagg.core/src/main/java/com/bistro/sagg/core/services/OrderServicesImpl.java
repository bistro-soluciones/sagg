package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.factory.OrderFactory;
import com.bistro.sagg.core.generator.SaleOrderNumberGenerator;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.OrderStatusConstants;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.PurchaseOrderItem;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.repository.PurchaseOrderRepository;
import com.bistro.sagg.core.repository.SaleOrderRepository;

public class OrderServicesImpl implements OrderServices {

	@Autowired
	private SaleOrderRepository saleOrderRepository;
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	public SaleOrder createSaleOrder(FranchiseBranch branch, Employee seller, List<SaleOrderItem> items) {
		String orderNumber = SaleOrderNumberGenerator.generateOrderNumber(branch, saleOrderRepository.getMaxOrderId());
		SaleOrder order = OrderFactory.createSaleOrder(branch, seller, orderNumber, items);
		saleOrderRepository.save(order);
		return order;
	}

	public void cancelSaleOrder(SaleOrder order) {
		order.setStatus(OrderStatusConstants.CANCELLED);
		saleOrderRepository.save(order);
	}

	public void deliverSaleOrder(SaleOrder order) {
		order.setStatus(OrderStatusConstants.DELIVERED);
		saleOrderRepository.save(order);
	}

	public PurchaseOrder createPurchaseOrder(FranchiseBranch branch, Supplier supplier, Employee receiver, List<PurchaseOrderItem> items) {
		String orderNumber = SaleOrderNumberGenerator.generateOrderNumber(branch, purchaseOrderRepository.getMaxOrderId());
		PurchaseOrder order = OrderFactory.createPurchaseOrder(branch, supplier, receiver, orderNumber, items);
		purchaseOrderRepository.save(order);
		return order;
	}

	public void cancelPurchaseOrder(PurchaseOrder order) {
		order.setStatus(OrderStatusConstants.CANCELLED);
		purchaseOrderRepository.save(order);
	}

	public void receivePurchaseOrder(PurchaseOrder order) {
		order.setStatus(OrderStatusConstants.RECEIVED);
		purchaseOrderRepository.save(order);
	}

}
