package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.SupplierBuilder;
import com.bistro.sagg.core.model.Supplier;
import com.bistro.sagg.core.repository.SupplierRepository;

public class SupplierServicesImpl implements SupplierServices {

	@Autowired
	private SupplierRepository supplierRepository;

	public void createSupplier(String businessName, String supplierId, String contactFirstname, String contactLastname,
			String contactEmail, String contactPhone, String contactCellphone) {
		// Create supplier object
		SupplierBuilder builder = new SupplierBuilder();
		builder.build(businessName, supplierId, contactFirstname, contactLastname, contactEmail, contactPhone,
				contactCellphone);
		Supplier supplier = builder.getSupplier();
		// Save supplier
		supplierRepository.save(supplier);
	}

	public List<Supplier> getSuppliers() {
		return (List<Supplier>) supplierRepository.findAll();
	}

}
