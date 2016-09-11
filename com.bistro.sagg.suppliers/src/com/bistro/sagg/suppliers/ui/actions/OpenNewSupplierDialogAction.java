package com.bistro.sagg.suppliers.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import com.bistro.sagg.suppliers.Activator;
import com.bistro.sagg.suppliers.ui.dialogs.NewSupplierDialog;

public class OpenNewSupplierDialogAction extends Action {

	private final IWorkbenchWindow window;

	public OpenNewSupplierDialogAction(String text, IWorkbenchWindow window) {
		super(text);
		this.window = window;
		// The id is used to refer to the action in a menu or toolbar
		setId(ISupplierCommandIds.CMD_OPEN_NEW_SUPPLIER);
		// Associate the action with a pre-defined command, to allow key
		// bindings.
		setActionDefinitionId(ISupplierCommandIds.CMD_OPEN_NEW_SUPPLIER);
		setImageDescriptor(Activator.getImageDescriptor("/icons/sample.gif"));
	}

	public void run() {
		new NewSupplierDialog(window.getShell()).open();
	}
}