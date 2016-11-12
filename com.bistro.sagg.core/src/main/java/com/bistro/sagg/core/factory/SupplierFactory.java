package com.bistro.sagg.core.factory;

import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.model.suppliers.SupplierContact;

public class SupplierFactory {

	private SupplierFactory() {
		super();
	}

	public static Supplier createSupplier(String businessName, String supplierId, String contactFirstname,
			String contactLastname, String contactEmail, String contactPhone, String contactCellphone,
			List<ProductCategory> categories, FranchiseBranch branch) {
		Supplier supplier = new Supplier();
		supplier = new Supplier();
		supplier.setBusinessName(businessName);
		supplier.setSupplierId(supplierId);
		supplier.setCategories(categories);
		SupplierContact contact = createContact(contactFirstname, contactLastname, contactEmail, contactPhone,
				contactCellphone);
		supplier.setContact(contact);
		supplier.setFranchiseBranch(branch);
		return supplier;
	}

	private static SupplierContact createContact(String contactFirstname, String contactLastname, String contactEmail,
			String contactPhone, String contactCellphone) {
		SupplierContact contact = new SupplierContact();
		contact.setFirstname(contactFirstname);
		contact.setLastname(contactLastname);
		contact.setEmail(contactEmail);
		contact.setPhone(contactPhone);
		contact.setCellphone(contactCellphone);
		return contact;
	}

}
