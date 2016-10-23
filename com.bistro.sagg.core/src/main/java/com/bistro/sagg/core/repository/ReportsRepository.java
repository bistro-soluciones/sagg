package com.bistro.sagg.core.repository;

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
				   "    pc.id = p.product_category_id", nativeQuery = true)
	List<Object> findProductsBySupplierAndCategory(@Param("branchId") Long branchId);
	
	@Query(value = "SELECT " +
				   "	bd.document_number AS NUMERO_VENTA, " +
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
				   "	billing_documents bd " +
				   "WHERE " +
				   "	pc.franchise_branch_id = :branchId AND " +
				   "	pc.id = p.product_category_id AND " +
				   "	p.id = soi.product_id AND " +
				   "	soi.order_id = so.id AND " +
				   "	bd.id = so.billing_document_id " +
				   "ORDER BY " +
				   "	so.order_number DESC, " +
				   "	bd.document_datetime DESC", nativeQuery = true)
	List<Object> findSalesDetailedByProduct(@Param("branchId") Long branchId);
	
	@Query(value = "SELECT " +
			   "		bd.document_number AS NUMERO_VENTA, " +
			   "		dt.name AS DOCUMENTO, " +
			   "		pm.name AS FORMA_PAGO, " +
			   "		bd.document_datetime AS FECHA_HORA, " +
			   "		concat(e.firstname, ' ', e.lastname) AS VENDEDOR, " +
			   "		so.order_status AS ESTADO, " +
			   "		(SELECT SUM(bi.unit_price) FROM billing_items bi WHERE bi.billing_document_id = bd.id) AS TOTAL " +
			   "	FROM " +
			   "		sale_orders so, " +
			   "		employees e, " +
			   "		billing_documents bd, " +
			   "		document_types dt, " +
			   "		payment_methods pm " +
			   "	WHERE " +
			   "		so.franchise_branch_id = :branchId AND " +
			   "		so.billing_document_id = bd.id AND " +
			   "		bd.document_type_id = dt.id AND " +
			   "		so.employee_id = e.id AND " +
			   "		bd.payment_method_id = pm.id", nativeQuery = true)
	List<Object> findSales(@Param("branchId") Long branchId);

}
