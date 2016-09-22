package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.suppliers.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {

}
