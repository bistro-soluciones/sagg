package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.company.employees.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {

}
