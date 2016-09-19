package com.bistro.sagg.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bistro.sagg.core.model.location.City;

public interface CityRepository extends CrudRepository<City, Long> {

	// @Query(value = "SELECT * FROM cities WHERE state_id = ?0", nativeQuery = true)
	@Query("select c from City c where c.state.id = :stateId")
	List<City> findAllByState(@Param("stateId") Long stateId);

}
