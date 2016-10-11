package com.bistro.sagg.sales.ui.utils;

public interface SalesCommunicationConstants {

	// DATA
	String ADD_PRODUCT_DATA = "PRODUCT_DATA";
	String ADD_PRODUCT_QUANTITY_DATA = "PRODUCT_QUANTITY_DATA";
	String SALE_ORDER_DATA = "SALE_ORDER_DATA";
	
	// EVENTS
	String ADD_PRODUCT_EVENT = "SalesPerspectiveCommunication/AddSelectedProduct";
	String CONFIRM_SALE_ORDER_EVENT = "SalesPerspectiveCommunication/ConfirmSaleOrder";
	String RESET_SALE_ORDER_EVENT = "SalesPerspectiveCommunication/ResetSaleOrder";
	
}
