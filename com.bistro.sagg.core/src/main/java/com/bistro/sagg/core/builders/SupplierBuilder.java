package com.bistro.sagg.core.builders;

import java.util.List;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.model.suppliers.SupplierContact;

public class SupplierBuilder {

	private Supplier supplier;

	public SupplierBuilder() {
		super();
		this.supplier = new Supplier();
	}

	public SupplierBuilder(Supplier supplier) {
		super();
		this.supplier = supplier;
	}

	public void build(String businessName, String supplierId, String contactFirstname, String contactLastname,
			String contactEmail, String contactPhone, String contactCellphone, List<ProductCategory> categories) {
		// Create supplier object
		this.supplier = new Supplier();
		this.supplier.setBusinessName(businessName);
		this.supplier.setSupplierId(supplierId);
		SupplierContact contact = new SupplierContact();
		contact.setFirstname(contactFirstname);
		contact.setLastname(contactLastname);
		contact.setEmail(contactEmail);
		contact.setPhone(contactPhone);
		contact.setCellphone(contactCellphone);
		supplier.setCategories(categories);
		this.supplier.setContact(contact);
	}

	public Supplier getSupplier() {
		return supplier;
	}

}
