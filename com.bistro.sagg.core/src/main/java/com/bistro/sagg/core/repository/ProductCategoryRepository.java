package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

	List<ProductCategory> findByBranch(FranchiseBranch branch);
	
}
