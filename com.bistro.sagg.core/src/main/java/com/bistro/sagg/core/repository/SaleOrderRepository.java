package com.bistro.sagg.core.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.order.SaleOrder;

public interface SaleOrderRepository extends CrudRepository<SaleOrder, Long> {

	@Query("select max(o.id) from SaleOrder o")
	Long getMaxOrderId();
	
}
