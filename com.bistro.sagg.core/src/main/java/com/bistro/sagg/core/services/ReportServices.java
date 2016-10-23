package com.bistro.sagg.core.services;

import java.util.Date;
import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;

public interface ReportServices extends ISaggService {

	List<Object> getProductsBySupplierAndCategory(FranchiseBranch branch, Supplier supplier, ProductCategory category, Product product);

	List<Object> getSales(FranchiseBranch branch, Date fromDate, Date toDate, DocumentType documentType, PaymentMethod paymentMethod, Employee employee);

	List<Object> getSalesDetailedByProduct(FranchiseBranch branch, Date fromDate, Date toDate, ProductCategory category, Product product);

}
