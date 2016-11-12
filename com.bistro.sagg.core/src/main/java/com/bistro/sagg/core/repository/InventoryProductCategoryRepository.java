package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.InventoryProductCategory;

public interface InventoryProductCategoryRepository extends CrudRepository<InventoryProductCategory, Long> {

	List<InventoryProductCategory> findByBranch(FranchiseBranch branch);
	
}
