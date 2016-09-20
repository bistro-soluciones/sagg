package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.Supplier;
import com.bistro.sagg.core.model.SupplierContact;
import com.bistro.sagg.core.repository.SupplierRepository;

public class SupplierServicesImpl implements SupplierServices {

	@Autowired
	private SupplierRepository supplierRepository;

	public void createSupplier(String businessName, String supplierId, String contactFirstname, String contactLastname,
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
		// Save supplier
		supplierRepository.save(supplier);
	}

	public List<Supplier> getSuppliers() {
		return (List<Supplier>) supplierRepository.findAll();
	}

}
