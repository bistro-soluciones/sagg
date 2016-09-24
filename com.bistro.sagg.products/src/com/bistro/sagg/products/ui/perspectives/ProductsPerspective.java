package com.bistro.sagg.products.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.products.ui.views.ProductCategoryListView;

public class ProductsPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.products.ui.perspectives.ProductsPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
//    	layout.addStandaloneView(EmployeeFilterView.ID, true, IPageLayout.TOP, 0.25f, editorArea);
    	layout.addStandaloneView(ProductCategoryListView.ID, true, IPageLayout.TOP, 1f, editorArea);

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
