package com.bistro.sagg.reports.ui.commands;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.bistro.sagg.reports.ui.utils.ReportsCommunicationConstants;
import com.bistro.sagg.reports.ui.views.SalesReportView;

public class SalesReportCommand implements IReportCommand {

	@Override
	public String getEvent() {
		return ReportsCommunicationConstants.GENERATE_SALES_REPORT_EVENT;
	}

	@Override
	public void execute() throws PartInitException {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(SalesReportView.ID);
	}

}
