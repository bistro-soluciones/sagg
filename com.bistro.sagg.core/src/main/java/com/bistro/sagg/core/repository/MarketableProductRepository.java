package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.products.MarketableProduct;

public interface MarketableProductRepository extends CrudRepository<MarketableProduct, Long> {

	@Query("select p from MarketableProduct p where p.category.id = :categoryId")
	List<MarketableProduct> findAllByCategory(@Param("categoryId") Long categoryId);
	
}
