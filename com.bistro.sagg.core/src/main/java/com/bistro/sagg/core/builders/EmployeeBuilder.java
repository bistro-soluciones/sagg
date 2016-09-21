package com.bistro.sagg.core.builders;

import java.util.Date;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;

public class EmployeeBuilder {

	private Employee employee;

	public EmployeeBuilder() {
		super();
		this.employee = new Employee();
	}

	public EmployeeBuilder(Employee employee) {
		super();
		this.employee = employee;
	}

	public void build(String firstname, String lastname, String personId, Position position, Date startDate,
			FranchiseBranch franchiseBranch, String email, String phone, String cellphone, String addressL1,
			String addressL2, City city) {
		this.employee.setFirstname(firstname);
		this.employee.setLastname(lastname);
		this.employee.setPersonId(personId);
		this.employee.setPosition(position);
		this.employee.setStartDate(startDate);
		this.employee.setFranchiseBranch(franchiseBranch);
		this.employee.setEmail(email);
		this.employee.setPhone(phone);
		this.employee.setCellphone(cellphone);
		this.employee.setAddressL1(addressL1);
		this.employee.setAddressL2(addressL2);
		this.employee.setCity(city);
	}

	public Employee getEmployee() {
		return employee;
	}

}
