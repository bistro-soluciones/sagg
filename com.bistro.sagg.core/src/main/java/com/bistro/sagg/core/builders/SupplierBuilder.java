package com.bistro.sagg.core.builders;

import com.bistro.sagg.core.model.Supplier;
import com.bistro.sagg.core.model.SupplierContact;

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
			String contactEmail, String contactPhone, String contactCellphone) {
		// Create supplier object
		Supplier supplier = new Supplier();
		supplier.setBusinessName(businessName);
		supplier.setSupplierId(supplierId);
		SupplierContact contact = new SupplierContact();
		contact.setFirstname(contactFirstname);
		contact.setLastname(contactLastname);
		contact.setEmail(contactEmail);
		contact.setPhone(contactPhone);
		contact.setCellphone(contactCellphone);
		supplier.setContact(contact);
	}

	public Supplier getSupplier() {
		return supplier;
	}

}
