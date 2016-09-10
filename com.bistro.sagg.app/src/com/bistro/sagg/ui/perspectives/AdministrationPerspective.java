package com.bistro.sagg.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.employees.ui.views.EmployeeView;

public class AdministrationPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.ui.perspectives.AdministrationPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
    	addFastViews(layout);
    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
    	layout.addStandaloneView(EmployeeView.ID, true, IPageLayout.TOP, 1f, editorArea);

    }
	private void addViewShortcuts(IPageLayout layout) {
		layout.addShowViewShortcut(EmployeeView.ID);
		layout.addShowViewShortcut("org.eclipse.ui.internal.introview");
	}
	private void addFastViews(IPageLayout layout) {
	}
}
