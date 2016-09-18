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
	// // Contact information
	@Column(name = "CONTACT_NAME")
	private String contactName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "CELLPHONE")
	private String cellphone;

	// // Address information
	// private String addressL1;
	// private String addressL2;
	// private City city;
	
	public Supplier() {
		super();
	}

}
