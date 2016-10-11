package com.bistro.sagg.core.services;

import java.util.Date;
import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;

public interface EmployeeServices extends ISaggService {

	void createEmployee(String firstname, String lastname, String personId, Position position, Date startDate,
			FranchiseBranch franchiseBranch, String email, String phone, String cellphone, String addressL1,
			String addressL2, City city);

	Employee getById(Long employeeId);
	
	List<Employee> getEmployees();

}
