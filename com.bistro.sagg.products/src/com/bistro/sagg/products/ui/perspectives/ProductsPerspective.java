package com.bistro.sagg.products.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.core.osgi.ui.utils.ViewUtils;
import com.bistro.sagg.products.ui.views.MarketableProductListView;
import com.bistro.sagg.products.ui.views.ProductCategoryListView;
import com.bistro.sagg.products.ui.views.SupplyListView;

public class ProductsPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.products.ui.perspectives.ProductsPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
    	
    	String editorArea = layout.getEditorArea();
    	
    	layout.addStandaloneView(ProductCategoryListView.ID, true, IPageLayout.RIGHT, 0.65f, editorArea);
    	layout.addStandaloneView(MarketableProductListView.ID, true, IPageLayout.TOP, 0.55f, editorArea);
    	layout.addStandaloneView(SupplyListView.ID, true, IPageLayout.TOP, 0.45f, editorArea);
    	
		ViewUtils.makeUncloseable(layout, ProductCategoryListView.ID, MarketableProductListView.ID, SupplyListView.ID);
    }
    
}
