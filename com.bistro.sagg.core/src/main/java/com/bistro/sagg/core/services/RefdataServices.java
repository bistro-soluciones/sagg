package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.model.location.Country;
import com.bistro.sagg.core.model.location.State;

public interface RefdataServices {

	List<Position> getPositions();

	List<State> getStatesByCountry(Country country);

	List<City> getCitiesByState(State state);

}
