package com.bistro.sagg.suppliers.ui.actions;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ISupplierCommandIds {

    public static final String CMD_OPEN_SUPPLIERS = "com.bistro.sagg.suppliers.ui.actions.OpenSuppliersViewAction";
    public static final String CMD_OPEN_NEW_SUPPLIER = "com.bistro.sagg.suppliers.ui.actions.OpenNewSupplierDialogAction";
    
}
