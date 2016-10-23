package com.bistro.sagg.reports.ui.viewers;

import java.util.Date;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ReportServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class SalesReportViewContentProvider extends SaggStructuredContentProvider {

	private Date fromDate;
	private Date toDate;
	private DocumentType documentType;
	private PaymentMethod paymentMethod;
	private Employee employee;
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		ReportServices reportServices = (ReportServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return reportServices.getSales(branch, fromDate, toDate, documentType, paymentMethod, employee).toArray();
	}

}
