package com.bistro.sagg.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.order.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

	@Query("select max(o.id) from PurchaseOrder o")
	Long getMaxOrderId();
	
}
