package com.bistro.sagg.employees.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.employees.ui.views.EmployeeDetailView;
import com.bistro.sagg.employees.ui.views.EmployeeListView;

public class EmployeesPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.employees.ui.perspectives.EmployeesPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
//    	layout.addStandaloneView(EmployeeFilterView.ID, true, IPageLayout.TOP, 0.25f, editorArea);
    	layout.addStandaloneView(EmployeeDetailView.ID, true, IPageLayout.RIGHT, 0.65f, editorArea);
    	layout.addStandaloneView(EmployeeListView.ID, true, IPageLayout.RIGHT, 0.35f, editorArea);

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
