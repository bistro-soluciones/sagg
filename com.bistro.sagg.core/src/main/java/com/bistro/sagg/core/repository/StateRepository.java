package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.State;

public interface StateRepository extends CrudRepository<Position, Long> {

//	@Query(value = "SELECT * FROM states WHERE country_id = :country_id", nativeQuery = true)
	@Query("select s from State s where s.country.id = :country_id")
	List<State> findAllByCountry(@Param("country_id") Long countryId);

}
