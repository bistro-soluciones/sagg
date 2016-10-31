package com.bistro.sagg.reports.ui.viewers;

import java.util.Date;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ReportServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class DetailedPurchaseOrdersReportViewContentProvider extends SaggStructuredContentProvider {

	private Date fromDate;
	private Date toDate;
	private Supplier supplier;
	private ProductCategory productCategory;
	private Product product;
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		ReportServices reportServices = (ReportServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return reportServices.getPurchaseOrdersDetailedByProducts(branch, fromDate, toDate, supplier, productCategory, product).toArray();
	}

}
