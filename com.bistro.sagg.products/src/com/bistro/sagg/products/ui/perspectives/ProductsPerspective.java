package com.bistro.sagg.products.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.products.ui.views.MarketableProductListView;
import com.bistro.sagg.products.ui.views.ProductCategoryListView;
import com.bistro.sagg.products.ui.views.MarketableProductDetailView;
import com.bistro.sagg.products.ui.views.SupplyDetailView;
import com.bistro.sagg.products.ui.views.SupplyListView;

public class ProductsPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.products.ui.perspectives.ProductsPerspective"; //$NON-NLS-1$

    public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
//    	addFastViews(layout);
//    	addViewShortcuts(layout);
    	
    	String editorArea = layout.getEditorArea();
//    	layout.addStandaloneView(ProductCategoryListView.ID, true, IPageLayout.LEFT, 0.25f, editorArea);
//    	layout.addStandaloneView(MarketableProductListView.ID, true, IPageLayout.TOP, 0.5f, editorArea);
//    	layout.addStandaloneView(SupplyListView.ID, true, IPageLayout.TOP, 0.5f, editorArea);
    	
    	layout.addStandaloneView(ProductCategoryListView.ID, true, IPageLayout.RIGHT, 0.65f, editorArea);
    	layout.addStandaloneView(MarketableProductListView.ID, true, IPageLayout.TOP, 0.55f, editorArea);
    	layout.addStandaloneView(SupplyListView.ID, true, IPageLayout.TOP, 0.45f, editorArea);
    	
    	layout.getViewLayout(ProductCategoryListView.ID).setCloseable(false);
    	layout.getViewLayout(MarketableProductListView.ID).setCloseable(false);
    	layout.getViewLayout(SupplyListView.ID).setCloseable(false);
    }
    
//	private void addViewShortcuts(IPageLayout layout) {
//		layout.addShowViewShortcut(MarketableProductDetailView.ID);
//		layout.addShowViewShortcut(SupplyDetailView.ID);
//	}
//	private void addFastViews(IPageLayout layout) {
//		layout.addFastView(MarketableProductDetailView.ID);
//		layout.addFastView(SupplyDetailView.ID);
//	}
    
}
