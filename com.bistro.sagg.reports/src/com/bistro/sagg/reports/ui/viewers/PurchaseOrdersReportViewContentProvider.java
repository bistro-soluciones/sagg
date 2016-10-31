package com.bistro.sagg.reports.ui.viewers;

import java.util.Date;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ReportServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class PurchaseOrdersReportViewContentProvider extends SaggStructuredContentProvider {

	private Date fromDate;
	private Date toDate;
	private Supplier supplier;
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		ReportServices reportServices = (ReportServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return reportServices.getPurchaseOrders(branch, fromDate, toDate, supplier).toArray();
	}

}
