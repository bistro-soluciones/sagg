package com.bistro.sagg.reports.ui.commands;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.bistro.sagg.reports.ui.utils.ReportsCommunicationConstants;
import com.bistro.sagg.reports.ui.views.PurchaseOrdersReportView;

public class PurchaseOrdersReportCommand implements IReportCommand {

	@Override
	public String getEvent() {
		return ReportsCommunicationConstants.GENERATE_PURCHASE_ORDERS_REPORT_EVENT;
	}
	
	@Override
	public void execute() throws PartInitException {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PurchaseOrdersReportView.ID);
	}

}
