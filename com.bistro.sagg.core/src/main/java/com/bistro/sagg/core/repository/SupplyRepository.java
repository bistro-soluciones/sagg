package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.products.Supply;

public interface SupplyRepository extends CrudRepository<Supply, Long> {

	@Query("select s from Supply s where s.category.id = :categoryId")
	List<Supply> findAllByCategory(@Param("categoryId") Long categoryId);
	
}
