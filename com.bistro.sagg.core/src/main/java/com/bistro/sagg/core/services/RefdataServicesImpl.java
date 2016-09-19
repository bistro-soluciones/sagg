package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.model.location.Country;
import com.bistro.sagg.core.model.location.State;
import com.bistro.sagg.core.repository.CityRepository;
import com.bistro.sagg.core.repository.PositionRepository;
import com.bistro.sagg.core.repository.StateRepository;

public class RefdataServicesImpl implements RefdataServices {

	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;

	public List<Position> getPositions() {
		return (List<Position>) positionRepository.findAll();
	}

	public List<State> getStatesByCountry(Country country) {
		return stateRepository.findAllByCountry(country.getId());
	}

	public List<City> getCitiesByState(State state) {
		return cityRepository.findAllByState(state.getId());
	}

}
