package com.bistro.sagg.products.ui.actions;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface IProductsCommandIds {

    public static final String CMD_OPEN_PRODUCT_CATEGORIES = "com.bistro.sagg.products.ui.actions.OpenProductCategoriesViewAction";
    public static final String CMD_OPEN_NEW_PRODUCT_CATEGORY = "com.bistro.sagg.products.ui.actions.OpenNewProductCategoryDialogAction";
    public static final String CMD_OPEN_MARKETABLE_PRODUCTS = "com.bistro.sagg.products.ui.actions.OpenMarketableProductsViewAction";
    public static final String CMD_OPEN_NEW_MARKETABLE_PRODUCT = "com.bistro.sagg.products.ui.actions.OpenNewMarketableProductDialogAction";
    
}
