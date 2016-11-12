package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

	List<Recipe> findByBranch(FranchiseBranch branch);
	
	List<Recipe> findByCategory(ProductCategory category);
	
}
