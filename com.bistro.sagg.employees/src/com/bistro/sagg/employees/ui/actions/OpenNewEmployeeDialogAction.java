package com.bistro.sagg.employees.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

import com.bistro.sagg.employees.Activator;
import com.bistro.sagg.employees.ui.dialogs.NewEmployeeDialog;


public class OpenNewEmployeeDialogAction extends Action {

    private final IWorkbenchWindow window;

    public OpenNewEmployeeDialogAction(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(IEmployeeCommandIds.CMD_OPEN_NEW_EMPLOYEE);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(IEmployeeCommandIds.CMD_OPEN_NEW_EMPLOYEE);
        setImageDescriptor(Activator.getImageDescriptor("/icons/sample.gif"));
    }

    public void run() {
    	new NewEmployeeDialog(window.getShell()).open();
    }
}