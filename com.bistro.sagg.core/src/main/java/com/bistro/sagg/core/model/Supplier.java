package com.bistro.sagg.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "BUSINESS_NAME")
	private String businessName;
	@Column(name = "SUPPLIER_ID")
	private String supplierId;
	// Contact information
	private SupplierContact contact;

	public Supplier() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public SupplierContact getContact() {
		return contact;
	}

	public void setContact(SupplierContact contact) {
		this.contact = contact;
	}

}
