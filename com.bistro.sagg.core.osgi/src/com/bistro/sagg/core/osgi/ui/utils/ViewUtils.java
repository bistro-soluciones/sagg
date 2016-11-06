package com.bistro.sagg.core.osgi.ui.utils;

import org.eclipse.ui.IPageLayout;

public class ViewUtils {

	public static void makeUncloseable(IPageLayout layout, String... viewIds) {
		for (String viewId : viewIds) {
			layout.getViewLayout(viewId).setCloseable(false);
		}
	}
	
}
