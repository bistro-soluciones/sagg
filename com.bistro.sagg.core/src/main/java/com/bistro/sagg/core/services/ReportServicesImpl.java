package com.bistro.sagg.core.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.Identificable;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.repository.ReportsRepository;

public class ReportServicesImpl implements ReportServices {

	@Autowired
	private ReportsRepository reportsRepository;

	public List<Object> getProductsBySupplierAndCategory(FranchiseBranch branch, Supplier supplier,
			ProductCategory category, Product product) {
		return reportsRepository.findProductsBySupplierAndCategory(getId(branch), getId(supplier), getId(category),
				getId(product));
	}

	public List<Object> getSales(FranchiseBranch branch, Date fromDate, Date toDate, DocumentType documentType,
			PaymentMethod paymentMethod, Employee employee) {
		return reportsRepository.findSales(getId(branch), fromDate, toDate, getId(documentType), getId(paymentMethod),
				getId(employee));
	}

	public List<Object> getSalesDetailedByProduct(FranchiseBranch branch, Date fromDate, Date toDate,
			ProductCategory category, Product product) {
		return reportsRepository.findSalesDetailedByProduct(getId(branch), fromDate, toDate, getId(category),
				getId(product));
	}

	public List<Object> getPurchaseOrders(FranchiseBranch branch, Date fromDate, Date toDate, Supplier supplier) {
		return reportsRepository.findPurchaseOrders(getId(branch), fromDate, toDate, getId(supplier));
	}

	public List<Object> getPurchaseOrdersDetailedByProducts(FranchiseBranch branch, Date fromDate, Date toDate,
			ProductCategory category, Product product) {
		return reportsRepository.findPurchaseOrdersDetailedByProducts(getId(branch), fromDate, toDate, getId(category),
				getId(product));
	}

	private Long getId(Identificable entity) {
		Long id = null;
		if (entity != null) {
			id = entity.getId();
		}
		return id;
	}

}
