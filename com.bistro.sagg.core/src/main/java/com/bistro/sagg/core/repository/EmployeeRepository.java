package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
