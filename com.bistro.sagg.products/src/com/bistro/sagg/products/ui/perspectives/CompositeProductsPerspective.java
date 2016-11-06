package com.bistro.sagg.products.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.bistro.sagg.core.osgi.ui.utils.ViewUtils;
import com.bistro.sagg.products.ui.views.BuildRecipeView;
import com.bistro.sagg.products.ui.views.RecipeListView;

public class CompositeProductsPerspective implements IPerspectiveFactory {

	public static final String ID = "com.bistro.sagg.products.ui.perspectives.CompositeProductsPerspective"; //$NON-NLS-1$

	public void createInitialLayout(IPageLayout layout) {
    	layout.setEditorAreaVisible(false);
    	
    	String editorArea = layout.getEditorArea();
    	layout.addStandaloneView(RecipeListView.ID, true, IPageLayout.LEFT, 0.52f, editorArea);
    	layout.addStandaloneView(BuildRecipeView.ID, true, IPageLayout.RIGHT, 0.48f, editorArea);
    	
    	ViewUtils.makeUncloseable(layout, RecipeListView.ID, BuildRecipeView.ID);
    }

}
