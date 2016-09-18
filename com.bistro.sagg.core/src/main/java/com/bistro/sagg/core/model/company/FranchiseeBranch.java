package com.bistro.sagg.core.model.company;

import java.util.List;

import com.bistro.sagg.core.model.Employee;
import com.bistro.sagg.core.model.Supplier;
import com.bistro.sagg.core.model.location.City;

public class FranchiseeBranch {

	private Long id;
	private String name;
	// Address information
	private String addressL1;
	private String addressL2;
	private City city;
	// Employees information
	private List<Employee> employees;
	// Suppliers information
	private List<Supplier> suppliers;
	
}
