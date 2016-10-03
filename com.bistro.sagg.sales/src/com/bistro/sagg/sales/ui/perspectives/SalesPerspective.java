package com.bistro.sagg.sales.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.sales.ui.views.ProductSelectionView;
import com.bistro.sagg.sales.ui.views.SalesDetailView;
import com.bistro.sagg.sales.ui.views.SalesView;

public class SalesPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.sales.ui.perspectives.SalesPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
    	layout.addStandaloneView(ProductSelectionView.ID, true, IPageLayout.LEFT, 0.35f, editorArea);
    	layout.addStandaloneView(SalesDetailView.ID, true, IPageLayout.TOP, 1f, editorArea);

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
