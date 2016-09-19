package com.bistro.sagg.core.model.company;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bistro.sagg.core.model.Supplier;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.location.City;

@Entity
@Table(name = "FANCHISE_BRANCHES")
public class FranchiseeBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	// Address information
	@Column(name = "ADDRESS_L1")
	private String addressL1;
	@Column(name = "ADDRESS_L2")
	private String addressL2;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	private City city;
	// Employees information
	@OneToMany(mappedBy = "franchiseBranch")
	private List<Employee> employees;

	// Suppliers information
	// private List<Supplier> suppliers;
	public FranchiseeBranch() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
