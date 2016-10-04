package com.bistro.sagg.sales.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.sales.ui.views.ProductSelectionView;
import com.bistro.sagg.sales.ui.views.SalesConfirmationDetailView;
import com.bistro.sagg.sales.ui.views.SalesDetailView;
import com.bistro.sagg.sales.ui.views.SalesView;

public class SalesPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.sales.ui.perspectives.SalesPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
//    	layout.addStandaloneView(ProductSelectionView.ID, true, IPageLayout.LEFT, 0.35f, editorArea);
//    	layout.addStandaloneView(SalesDetailView.ID, true, IPageLayout.LEFT, 0.5f, editorArea);
//    	layout.addStandaloneView(SalesConfirmationDetailView.ID, false, IPageLayout.RIGHT, 0.2f, editorArea);
    	layout.addStandaloneView(SalesConfirmationDetailView.ID, true, IPageLayout.RIGHT, 0.65f, editorArea);
    	layout.addStandaloneView(ProductSelectionView.ID, true, IPageLayout.TOP, 0.45f, editorArea);
    	layout.addStandaloneView(SalesDetailView.ID, true, IPageLayout.TOP, 0.55f, editorArea);

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
