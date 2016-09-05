package com.bistro.sagg.rcp.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.rcp.views.EmployeeView;

public class AdministrationPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.rcp.perspectives.administration"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	addFastViews(layout);
    	addViewShortcuts(layout);
	}
	private void addViewShortcuts(IPageLayout layout) {
		layout.addShowViewShortcut(EmployeeView.ID);
	}
	private void addFastViews(IPageLayout layout) {
	}
}
