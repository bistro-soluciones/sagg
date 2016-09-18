package com.bistro.sagg.core.model.company;

import com.bistro.sagg.core.model.location.City;

public class Franchised {

	private Long id;
	// Personal information
	private String firstname;
	private String lastname;
	private String personId;
	// Contact information
	private String email;
	private String phone;
	private String cellphone;
	// Address information
	private String addressL1;
	private String addressL2;
	private City city;
	// Franchised branch

	public Franchised() {
		super();
	}

}
