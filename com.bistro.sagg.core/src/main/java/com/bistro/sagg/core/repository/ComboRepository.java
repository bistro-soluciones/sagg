package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.Combo;

public interface ComboRepository extends CrudRepository<Combo, Long> {

	List<Combo> findByBranch(FranchiseBranch branch);
	
}
