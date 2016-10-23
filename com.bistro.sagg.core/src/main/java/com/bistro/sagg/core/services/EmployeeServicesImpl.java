package com.bistro.sagg.core.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.EmployeeBuilder;
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
		EmployeeBuilder builder = new EmployeeBuilder();
		builder.build(firstname, lastname, personId, position, startDate, franchiseBranch, email, phone, cellphone,
				addressL1, addressL2, city);
		Employee employee = builder.getEmployee();
		// Save employee
		employeeRepository.save(employee);
	}

	public Employee getById(Long employeeId) {
		return employeeRepository.findOne(employeeId);
	}

	public List<Employee> getEmployees(FranchiseBranch branch) {
		return (List<Employee>) employeeRepository.findByFranchiseBranch(branch);
	}

}
