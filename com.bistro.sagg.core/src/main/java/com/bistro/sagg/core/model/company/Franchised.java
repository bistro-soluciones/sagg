package com.bistro.sagg.core.model.company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.location.City;

@Entity
@Table(name = "FANCHISEDS")
public class Franchised {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	// Personal information
	@Column(name = "FIRSTNAME")
	private String firstname;
	@Column(name = "LASTNAME")
	private String lastname;
	@Column(name = "PERSON_ID")
	private String personId;
	// Contact information
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "CELLPHONE")
	private String cellphone;
	// Address information
	@Column(name = "ADDRESS_L1")
	private String addressL1;
	@Column(name = "ADDRESS_L2")
	private String addressL2;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	private City city;
	// Franchise information
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRANCHISE_ID")
	private Franchise franchise;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FRANCHISE_BRANCH_ID")
	private FranchiseeBranch franchiseeBranch;

	public Franchised() {
		super();
	}

}
