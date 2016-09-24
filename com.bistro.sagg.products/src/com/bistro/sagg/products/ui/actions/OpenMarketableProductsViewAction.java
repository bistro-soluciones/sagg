package com.bistro.sagg.products.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import com.bistro.sagg.products.Activator;

public class OpenMarketableProductsViewAction extends Action {

	private final IWorkbenchWindow window;
	private int instanceNum = 0;
	private final String viewId;

	public OpenMarketableProductsViewAction(IWorkbenchWindow window, String label, String viewId) {
		this.window = window;
		this.viewId = viewId;
		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(IProductsCommandIds.CMD_OPEN_MARKETABLE_PRODUCTS);
		// Associate the action with a pre-defined command, to allow key
		// bindings.
		setActionDefinitionId(IProductsCommandIds.CMD_OPEN_MARKETABLE_PRODUCTS);
		setImageDescriptor(Activator.getImageDescriptor("/icons/sample2.gif"));
	}

	public void run() {
		if (window != null) {
			try {
				window.getActivePage().showView(viewId, Integer.toString(instanceNum++), IWorkbenchPage.VIEW_ACTIVATE);
			} catch (PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error", "Error opening view:" + e.getMessage());
			}
		}
	}
}
