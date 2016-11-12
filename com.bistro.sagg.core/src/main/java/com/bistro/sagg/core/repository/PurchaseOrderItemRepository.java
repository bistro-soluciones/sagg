package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.order.PurchaseOrderItem;

public interface PurchaseOrderItemRepository extends CrudRepository<PurchaseOrderItem, Long> {

	@Query("from PurchaseOrderItem i where i.product.id = :productId and i.availableStock > 0")
	List<PurchaseOrderItem> getPurchasesWithStock(@Param("productId") Long productId);
	
}
