package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

	List<Supplier> findByFranchiseBranch(FranchiseBranch franchiseBranch);

}
