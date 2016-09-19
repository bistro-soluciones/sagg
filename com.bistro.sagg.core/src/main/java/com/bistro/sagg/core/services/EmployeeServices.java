package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.company.employees.Employee;

public interface EmployeeServices {

	void createEmployee(String firstname, String lastname, String personId, String email, String phone, String cellphone);

	List<Employee> getEmployees();
	
}
