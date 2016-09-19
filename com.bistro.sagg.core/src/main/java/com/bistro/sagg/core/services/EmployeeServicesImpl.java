package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.repository.EmployeeRepository;

public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void createEmployee(String firstname, String lastname, String personId, String email, String phone,
			String cellphone) {
		// Create employee object
		Employee employee = new Employee();
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setPersonId(personId);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setCellphone(cellphone);
		// Save employee
		employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

}
