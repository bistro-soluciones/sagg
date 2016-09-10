package com.bistro.sagg.employees.ui.actions;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface IEmployeeCommandIds {

    public static final String CMD_OPEN_EMPLOYEES = "com.bistro.sagg.ui.actions.OpenEmployeesViewAction";
    public static final String CMD_OPEN_NEW_EMPLOYEE = "com.bistro.sagg.ui.actions.OpenNewEmployeeDialogAction";
    
}
