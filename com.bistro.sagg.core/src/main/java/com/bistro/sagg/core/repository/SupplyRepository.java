package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Supply;

public interface SupplyRepository extends CrudRepository<Supply, Long> {

//	@Query("select s from Supply s where s.category.id = :categoryId")
//	List<Supply> findAllByCategory(@Param("categoryId") Long categoryId);

	List<Supply> findByCategory(ProductCategory category);
	
//	@Query(value = "SELECT " +
//				   "	s.business_name AS PROVEEDOR, " +
//				   "    s.supplier_id AS RUT_PROVEEDOR, " +
//				   "    p.name AS PRODUCTO, " +
//				   "    pc.name AS CATEGORIA " +
//				   "FROM " +
//				   "	suppliers s, " +
//				   "	products p, " +
//				   "    product_categories pc, " +
//				   "    suppliers_for_categories sfc " +
//				   "WHERE " +
//				   "	s.franchise_branch_id = :branchId AND " +
//				   " 	s.id = sfc.supplier_id AND " +
//				   "    sfc.product_category_id = pc.id AND " +
//				   "    pc.id = p.product_category_id", nativeQuery = true)
//	List<Object> findProductsBySupplierAndCategory(@Param("branchId") Long branchId);

}
