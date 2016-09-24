package com.bistro.sagg.products.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import com.bistro.sagg.products.Activator;
import com.bistro.sagg.products.ui.dialogs.NewProductCategoryDialog;

public class OpenNewProductCategoryDialogAction extends Action {

	private final IWorkbenchWindow window;

	public OpenNewProductCategoryDialogAction(String text, IWorkbenchWindow window) {
		super(text);
		this.window = window;
		// The id is used to refer to the action in a menu or toolbar
		setId(IProductsCommandIds.CMD_OPEN_NEW_PRODUCT_CATEGORY);
		// Associate the action with a pre-defined command, to allow key
		// bindings.
		setActionDefinitionId(IProductsCommandIds.CMD_OPEN_NEW_PRODUCT_CATEGORY);
		setImageDescriptor(Activator.getImageDescriptor("/icons/sample.gif"));
	}

	public void run() {
		new NewProductCategoryDialog(window.getShell()).open();
	}
}