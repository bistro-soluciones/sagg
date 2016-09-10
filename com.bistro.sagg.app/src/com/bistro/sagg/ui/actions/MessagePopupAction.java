package com.bistro.sagg.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

import com.bistro.sagg.Activator;
import com.bistro.sagg.employees.ui.actions.IEmployeeCommandIds;


public class MessagePopupAction extends Action {

    private final IWorkbenchWindow window;

    public MessagePopupAction(String text, IWorkbenchWindow window) {
        super(text);
        this.window = window;
        // The id is used to refer to the action in a menu or toolbar
        setId(IEmployeeCommandIds.CMD_OPEN_EMPLOYEES);
        // Associate the action with a pre-defined command, to allow key bindings.
        setActionDefinitionId(IEmployeeCommandIds.CMD_OPEN_EMPLOYEES);
        setImageDescriptor(Activator.getImageDescriptor("/icons/sample3.gif"));
    }

    public void run() {
        MessageDialog.openInformation(window.getShell(), "Open", "Open Message Dialog!");
    }
}