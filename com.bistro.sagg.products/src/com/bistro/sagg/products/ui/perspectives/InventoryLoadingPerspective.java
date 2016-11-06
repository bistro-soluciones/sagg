package com.bistro.sagg.products.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.core.osgi.ui.utils.ViewUtils;
import com.bistro.sagg.products.ui.views.InventoryLoadingConfirmationDetailView;
import com.bistro.sagg.products.ui.views.InventoryLoadingDetailView;
import com.bistro.sagg.products.ui.views.ProductSelectionView;

public class InventoryLoadingPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.products.ui.perspectives.InventoryLoadingPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
    	
    	String editorArea = layout.getEditorArea();
    	layout.addStandaloneView(InventoryLoadingConfirmationDetailView.ID, true, IPageLayout.RIGHT, 0.65f, editorArea);
    	layout.addStandaloneView(ProductSelectionView.ID, true, IPageLayout.TOP, 0.45f, editorArea);
    	layout.addStandaloneView(InventoryLoadingDetailView.ID, true, IPageLayout.TOP, 0.55f, editorArea);

		ViewUtils.makeUncloseable(layout, InventoryLoadingConfirmationDetailView.ID, ProductSelectionView.ID, InventoryLoadingDetailView.ID);
    }

}
