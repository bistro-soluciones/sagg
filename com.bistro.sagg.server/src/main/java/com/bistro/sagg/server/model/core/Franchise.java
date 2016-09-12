package com.bistro.sagg.server.model.core;

import java.util.List;

import com.bistro.sagg.server.model.Employee;
import com.bistro.sagg.server.model.Supplier;
import com.bistro.sagg.server.model.core.location.City;

public class Franchise {

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
