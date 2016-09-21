package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.Supplier;

public interface SupplierServices extends ISaggService {

	void createSupplier(String businessName, String supplierId, String contactFirstname, String contactLastname,
			String contactEmail, String contactPhone, String contactCellphone);

	List<Supplier> getSuppliers();

}
