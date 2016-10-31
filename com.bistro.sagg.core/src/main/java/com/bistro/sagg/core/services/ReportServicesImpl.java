package com.bistro.sagg.core.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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
		return reportsRepository.findProductsBySupplierAndCategory(branch.getId());
	}

	public List<Object> getSales(FranchiseBranch branch, Date fromDate, Date toDate, DocumentType documentType,
			PaymentMethod paymentMethod, Employee employee) {
		return reportsRepository.findSales(branch.getId());
	}

	public List<Object> getSalesDetailedByProduct(FranchiseBranch branch, Date fromDate, Date toDate,
			ProductCategory category, Product product) {
		return reportsRepository.findSalesDetailedByProduct(branch.getId());
	}

	public List<Object> getPurchaseOrders(FranchiseBranch branch, Date fromDate, Date toDate, Supplier supplier) {
		return reportsRepository.findPurchaseOrders(branch.getId());
	}

	public List<Object> getPurchaseOrdersDetailedByProducts(FranchiseBranch branch, Date fromDate, Date toDate,
			Supplier supplier, ProductCategory category, Product product) {
		return reportsRepository.findPurchaseOrdersDetailedByProducts(branch.getId());
	}

}
