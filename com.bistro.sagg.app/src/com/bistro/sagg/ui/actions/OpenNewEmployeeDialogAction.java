package com.bistro.sagg.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import com.bistro.sagg.Activator;
import com.bistro.sagg.employees.views.NewEmployeeDialog;


public class OpenNewEmployeeDialogAction extends Action {

    private final IWorkbenchWindow window;

    public OpenNewEmployeeDialogAction(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(ICommandIds.CMD_OPEN_EMPLOYEES);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(ICommandIds.CMD_OPEN_EMPLOYEES);
        setImageDescriptor(Activator.getImageDescriptor("/icons/sample3.gif"));
    }

    public void run() {
    	new NewEmployeeDialog(window.getShell()).open();
    }
}