package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.SaleProductCategory;

public interface SaleProductCategoryRepository extends CrudRepository<SaleProductCategory, Long> {

	List<SaleProductCategory> findByBranch(FranchiseBranch branch);
	
}
