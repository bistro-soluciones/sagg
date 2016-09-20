package com.bistro.sagg.core.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.repository.EmployeeRepository;

public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void createEmployee(String firstname, String lastname, String personId, Position position, Date startDate,
			FranchiseBranch franchiseBranch, String email, String phone, String cellphone, String addressL1,
			String addressL2, City city) {
		// Create employee object
		Employee employee = new Employee();
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setPersonId(personId);
		employee.setPosition(position);
		employee.setStartDate(startDate);
		employee.setFranchiseBranch(franchiseBranch);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setCellphone(cellphone);
		employee.setAddressL1(addressL1);
		employee.setAddressL2(addressL2);
		employee.setCity(city);
		// Save employee
		employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

}
