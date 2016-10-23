package com.bistro.sagg.reports.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.reports.ui.views.DetailedSalesReportView;
import com.bistro.sagg.reports.ui.views.ReportConfigurationView;
import com.bistro.sagg.reports.ui.views.SalesReportView;
import com.bistro.sagg.reports.ui.views.UtilitySalesReportView;

public class ReportsPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.reports.ui.perspectives.ReportsPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
//    	layout.addStandaloneView(EmployeeFilterView.ID, true, IPageLayout.TOP, 0.25f, editorArea);
    	layout.addStandaloneView(ReportConfigurationView.ID, true, IPageLayout.LEFT, 0.3f, editorArea);
    	layout.addStandaloneView(DetailedSalesReportView.ID, true, IPageLayout.LEFT, 0.7f, editorArea);

    }
//	private void addViewShortcuts(IPageLayout layout) {
//		layout.addShowViewShortcut(EmployeeView.ID);
//		layout.addShowViewShortcut(SupplierView.ID);
//	}
//	private void addFastViews(IPageLayout layout) {
//		layout.addShowViewShortcut(EmployeeView.ID);
//		layout.addShowViewShortcut(SupplierView.ID);
//	}
}
