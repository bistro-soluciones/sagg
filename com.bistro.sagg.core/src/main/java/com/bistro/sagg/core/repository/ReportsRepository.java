package com.bistro.sagg.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.company.Franchise;

public interface ReportsRepository extends JpaRepository<Franchise, Long> {

	@Query(value = "SELECT " +
				   "	s.business_name AS PROVEEDOR, " +
				   "    s.supplier_id AS RUT_PROVEEDOR, " +
				   "    p.name AS PRODUCTO, " +
				   "    pc.name AS CATEGORIA " +
				   "FROM " +
				   "	suppliers s, " +
				   "	products p, " +
				   "    product_categories pc, " +
				   "    suppliers_for_categories sfc " +
				   "WHERE " +
				   "	s.franchise_branch_id = :branchId AND " +
				   " 	s.id = sfc.supplier_id AND " +
				   "    sfc.product_category_id = pc.id AND " +
				   "    pc.id = p.product_category_id AND " +
				   "	CASE WHEN (:supplierId IS NOT NULL) THEN s.id = :supplierId ELSE TRUE END AND " +
				   "	CASE WHEN (:productCategoryId IS NOT NULL) THEN pc.id = :productCategoryId ELSE TRUE END AND " +
				   "	CASE WHEN (:productId IS NOT NULL) THEN p.id = :productId ELSE TRUE END", nativeQuery = true)
	List<Object> findProductsBySupplierAndCategory(@Param("branchId") Long branchId,
			@Param("supplierId") Long supplierId, @Param("productCategoryId") Long productCategoryId,
			@Param("productId") Long productId);
	
	@Query(value = "SELECT " +
				   "	sbd.document_number AS NUMERO_VENTA, " +
				   "	sbd.document_datetime AS FECHA_HORA, " +
				   "	p.name AS PRODUCTO, " +
				   "	pc.name AS CATEGORIA, " +
				   "	p.unit_sales_price AS PRECIO_UNITARIO, " +
				   "	soi.quantity AS CANTIDAD, " +
				   "	soi.amount AS TOTAL " +
				   "FROM " +
				   "	product_categories pc, " +
				   "	products p, " +
				   "	sale_orders so, " +
				   "	sale_order_items soi, " +
				   "	sale_billing_documents sbd " +
				   "WHERE " +
				   "	pc.franchise_branch_id = :branchId AND " +
				   "	pc.id = p.product_category_id AND " +
				   "	p.id = soi.product_id AND " +
				   "	soi.order_id = so.id AND " +
				   "	sbd.id = so.billing_document_id AND " +
				   "	CASE WHEN (:fromDate IS NOT NULL) THEN so.order_datetime >= :fromDate ELSE TRUE END AND " +
				   "	CASE WHEN (:toDate IS NOT NULL) THEN so.order_datetime <= :toDate + INTERVAL 1 DAY ELSE TRUE END AND " +
				   "	CASE WHEN (:productCategoryId IS NOT NULL) THEN pc.id = :productCategoryId ELSE TRUE END AND " +
				   "	CASE WHEN (:productId IS NOT NULL) THEN p.id = :productId ELSE TRUE END " +
				   "ORDER BY " +
				   "	so.order_number DESC, " +
				   "	sbd.document_datetime DESC", nativeQuery = true)
	List<Object> findSalesDetailedByProduct(@Param("branchId") Long branchId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("productCategoryId") Long productCategoryId,
			@Param("productId") Long productId);
	
	@Query(value = "SELECT " +
				   "	sbd.document_number AS NUMERO_VENTA, " +
				   "	dt.name AS DOCUMENTO, " +
				   "	pm.name AS FORMA_PAGO, " +
				   "	sbd.document_datetime AS FECHA_HORA, " +
				   "	concat(e.firstname, ' ', e.lastname) AS VENDEDOR, " +
				   "	so.order_status AS ESTADO, " +
				   "	(SELECT SUM(sbi.unit_price) FROM sale_billing_items sbi WHERE sbi.billing_document_id = sbd.id) AS TOTAL " +
				   "FROM " +
				   "	sale_orders so, " +
				   "	employees e, " +
				   "	sale_billing_documents sbd, " +
				   "	document_types dt, " +
				   "	payment_methods pm " +
				   "WHERE " +
				   "	so.franchise_branch_id = :branchId AND " +
				   "	so.billing_document_id = sbd.id AND " +
				   "	sbd.document_type_id = dt.id AND " +
				   "	so.employee_id = e.id AND " +
				   "	sbd.payment_method_id = pm.id AND " +
				   "	CASE WHEN (:fromDate IS NOT NULL) THEN so.order_datetime >= :fromDate ELSE TRUE END AND " +
				   "	CASE WHEN (:toDate IS NOT NULL) THEN so.order_datetime <= :toDate + INTERVAL 1 DAY ELSE TRUE END AND " +
				   "	CASE WHEN (:documentTypeId IS NOT NULL) THEN dt.id = :documentTypeId ELSE TRUE END AND " +
				   "	CASE WHEN (:paymentMethodId IS NOT NULL) THEN pm.id = :paymentMethodId ELSE TRUE END AND " +
				   "	CASE WHEN (:employeeId IS NOT NULL) THEN e.id = :employeeId ELSE TRUE END", nativeQuery = true)
	List<Object> findSales(@Param("branchId") Long branchId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("documentTypeId") Long documentTypeId,
			@Param("paymentMethodId") Long paymentMethodId, @Param("employeeId") Long employeeId);
	
	@Query(value = "SELECT " +
				   "	po.order_number AS NRO_ORDEN," +
				   "	po.order_datetime AS FECHA_HORA, " +
				   "	po.order_status AS ESTADO, " +
				   "	s.business_name AS PROVEEDOR, " +
				   "	concat(es.firstname, ' ', es.lastname) AS SOLICITANTE, " +
				   "	concat(er.firstname, ' ', er.lastname) AS RECPTOR " +
				   "FROM " +
				   "	purchase_orders po, " +
				   "	employees es, " +
				   "	employees er, " +
				   "	suppliers s " +
				   "WHERE " +
				   "	po.franchise_branch_id = :branchId AND " +
				   "	po.supplier_id = s.id AND " +
				   "	po.receiver_id = er.id AND " +
				   "	po.requestor_id = es.id AND " +
				   "	CASE WHEN (:fromDate IS NOT NULL) THEN po.order_datetime >= :fromDate ELSE TRUE END AND " +
				   "	CASE WHEN (:toDate IS NOT NULL) THEN po.order_datetime <= :toDate + INTERVAL 1 DAY ELSE TRUE END AND " +
				   "	CASE WHEN (:supplierId IS NOT NULL) THEN s.id = :supplierId ELSE TRUE END", nativeQuery = true)
	List<Object> findPurchaseOrders(@Param("branchId") Long branchId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("supplierId") Long supplierId);

	@Query(value = "SELECT " +
				   "	po.order_number AS NRO_ORDEN, " +
				   "	po.order_datetime AS FECHA_HORA, " +
				   "	p.name AS PRODUCTO, " +
				   "	pc.name AS CATEGORIA, " +
				   "	poi.purchase_unit_price AS PRECIO_UNITARIO, " +
				   "	poi.quantity AS CANTIDAD, " +
				   "	poi.amount AS TOTAL " +
				   "FROM " +
				   "	purchase_orders po, " +
				   "	purchase_order_items poi, " +
				   "	product_categories pc, " +
				   "	products p " +
				   "WHERE " +
				   "	po.franchise_branch_id = :branchId AND " +
				   "	po.id = poi.order_id AND " +
				   "	pc.franchise_branch_id = :branchId AND " +
				   "	pc.id = p.product_category_id AND " +
				   "	p.id = poi.product_id AND " +
				   "	CASE WHEN (:fromDate IS NOT NULL) THEN po.order_datetime >= :fromDate ELSE TRUE END AND " +
				   "	CASE WHEN (:toDate IS NOT NULL) THEN po.order_datetime <= :toDate + INTERVAL 1 DAY ELSE TRUE END AND " +
				   "	CASE WHEN (:productCategoryId IS NOT NULL) THEN pc.id = :productCategoryId ELSE TRUE END AND " +
				   "	CASE WHEN (:productId IS NOT NULL) THEN p.id = :productId ELSE TRUE END " +
				   "ORDER BY " +
				   "	po.order_number ASC", nativeQuery = true)
	List<Object> findPurchaseOrdersDetailedByProducts(@Param("branchId") Long branchId,
			@Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("productCategoryId") Long productCategoryId, @Param("productId") Long productId);
	
}
