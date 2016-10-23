package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findByFranchiseBranch(FranchiseBranch franchiseBranch);

}
