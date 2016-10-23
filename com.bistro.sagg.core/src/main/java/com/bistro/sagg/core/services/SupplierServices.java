package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;

public interface SupplierServices extends ISaggService {

	void createSupplier(String businessName, String supplierId, String contactFirstname, String contactLastname,
			String contactEmail, String contactPhone, String contactCellphone, List<ProductCategory> categories);

	List<Supplier> getSuppliers(FranchiseBranch branch);

}
