package com.bistro.sagg.products.ui.utils;

public interface ProductsCommunicationConstants {

	// DATA
	String ADD_PRODUCT_SUPPLIER_DATA = "ADD_PRODUCT_SUPPLIER_DATA";
	String ADD_PRODUCT_CATEGORY_DATA = "ADD_PRODUCT_CATEGORY_DATA";
	String ADD_MARKETABLE_PRODUCT_DATA = "ADD_MARKETABLE_PRODUCT_DATA";
	String ADD_SUPPLY_DATA = "ADD_SUPPLY_DATA";
	String ADD_PRODUCT_DATA = "ADD_PRODUCT_DATA";
	String ADD_PRODUCT_QUANTITY_DATA = "ADD_PRODUCT_QUANTITY_DATA";
	String ADD_PRODUCT_UNIT_PRICE_DATA = "ADD_PRODUCT_UNIT_PRICE_DATA";
	String PURCHASE_ORDER_DATA = "PURCHASE_ORDER_DATA";
	
	// EVENTS
	String ADD_PRODUCT_CATEGORY_EVENT = "ProductsCommunicationConstants/AddProductCategory";
	String ADD_MARKETABLE_PRODUCT_EVENT = "ProductsCommunicationConstants/AddMarketableProduct";
	String ADD_SUPPLY_EVENT = "ProductsCommunicationConstants/AddSupply";
	String ADD_PRODUCT_EVENT = "ProductsCommunicationConstants/AddProduct";
	String CONFIRM_PURCHASE_ORDER_EVENT = "ProductsCommunicationConstants/ConfirmPurchaseOrder";
	String RESET_PURCHASE_ORDER_EVENT = "ProductsCommunicationConstants/ResetPurchaseOrder";
	
}
