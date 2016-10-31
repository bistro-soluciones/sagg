package com.bistro.sagg.products.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.products.ui.views.InventoryLoadingConfirmationDetailView;
import com.bistro.sagg.products.ui.views.InventoryLoadingDetailView;
import com.bistro.sagg.products.ui.views.ProductSelectionView;

public class InventoryLoadingPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.products.ui.perspectives.InventoryLoadingPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
//    	layout.addStandaloneView(ProductSelectionView.ID, true, IPageLayout.LEFT, 0.35f, editorArea);
//    	layout.addStandaloneView(SalesDetailView.ID, true, IPageLayout.LEFT, 0.5f, editorArea);
//    	layout.addStandaloneView(SalesConfirmationDetailView.ID, false, IPageLayout.RIGHT, 0.2f, editorArea);
    	layout.addStandaloneView(InventoryLoadingConfirmationDetailView.ID, true, IPageLayout.RIGHT, 0.65f, editorArea);
    	layout.addStandaloneView(ProductSelectionView.ID, true, IPageLayout.TOP, 0.45f, editorArea);
    	layout.addStandaloneView(InventoryLoadingDetailView.ID, true, IPageLayout.TOP, 0.55f, editorArea);

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
