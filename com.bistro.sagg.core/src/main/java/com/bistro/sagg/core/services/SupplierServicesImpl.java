package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.factory.SupplierFactory;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.repository.SupplierRepository;

public class SupplierServicesImpl implements SupplierServices {

	@Autowired
	private SupplierRepository supplierRepository;

	public void createSupplier(String businessName, String supplierId, String contactFirstname, String contactLastname,
			String contactEmail, String contactPhone, String contactCellphone, List<ProductCategory> categories,
			FranchiseBranch branch) {
		// Create supplier object
		Supplier supplier = SupplierFactory.createSupplier(businessName, supplierId, contactFirstname, contactLastname,
				contactEmail, contactPhone, contactCellphone, categories, branch);
		// Save supplier
		supplierRepository.save(supplier);
	}

	public List<Supplier> getSuppliers(FranchiseBranch branch) {
		return (List<Supplier>) supplierRepository.findByFranchiseBranch(branch);
	}

}
