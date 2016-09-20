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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRANCHISE_BRANCH_ID")
	private FranchiseBranch franchiseeBranch;

	public Franchised() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddressL1() {
		return addressL1;
	}

	public void setAddressL1(String addressL1) {
		this.addressL1 = addressL1;
	}

	public String getAddressL2() {
		return addressL2;
	}

	public void setAddressL2(String addressL2) {
		this.addressL2 = addressL2;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Franchise getFranchise() {
		return franchise;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

	public FranchiseBranch getFranchiseeBranch() {
		return franchiseeBranch;
	}

	public void setFranchiseeBranch(FranchiseBranch franchiseeBranch) {
		this.franchiseeBranch = franchiseeBranch;
	}

}
