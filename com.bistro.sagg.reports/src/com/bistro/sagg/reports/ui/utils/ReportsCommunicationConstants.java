package com.bistro.sagg.reports.ui.utils;

public interface ReportsCommunicationConstants {

	// DATA
	String FROM_DATE_DATA = "START_DATE_DATA";
	String TO_DATE_DATA = "END_DATE_DATA";
	String DOCUMENT_TYPE_DATA = "DOCUMENT_TYPE_DATA";
	String PAYMENT_METHOD_DATA = "PAYMENT_METHOD_DATA";
	String EMPLOYEE_DATA = "EMPLOYEE_DATA";
	String SUPPLIER_DATA = "SUPPLIER_DATA";
	String PRODUCT_CATEGORY_DATA = "PRODUCT_CATEGORY_DATA";
	String PRODUCT_DATA = "PRODUCT_DATA";
	
	// EVENTS
	String GENERATE_SALES_REPORT_EVENT = "ReportsCommunicationConstants/SalesReport";
	String GENERATE_DETAILED_SALES_REPORT_EVENT = "ReportsCommunicationConstants/DetailedSalesReport";
	String GENERATE_SUPPLIES_BY_SUPPLIER_REPORT_EVENT = "ReportsCommunicationConstants/SuppliesBySupplierReport";
	
}
