package com.bistro.sagg.suppliers.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.suppliers.ui.views.SupplierFilterView;
import com.bistro.sagg.suppliers.ui.views.SupplierListView;

public class SuppliersPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.suppliers.ui.perspectives.SuppliersPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
    	layout.addStandaloneView(SupplierFilterView.ID, true, IPageLayout.TOP, 0.25f, editorArea);
    	layout.addStandaloneView(SupplierListView.ID, true, IPageLayout.TOP, 0.75f, editorArea);

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
