package com.bistro.sagg.ui.actions;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

    public static final String CMD_OPEN = "com.bistro.test.mail.open";
    public static final String CMD_OPEN_MESSAGE = "com.bistro.test.mail.openMessage";
    
    // Bistro Commands
    public static final String CMD_OPEN_EMPLOYEES = "com.bistro.sagg.ui.actions.OpenEmployeesViewAction";
    
}
