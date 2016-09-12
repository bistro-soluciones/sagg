package com.bistro.sagg.server.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.server.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
