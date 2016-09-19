package com.bistro.sagg.employees.ui.viewers;

import java.util.Collections;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bistro.sagg.core.model.location.State;
import com.bistro.sagg.core.services.RefdataServices;

public class CityComboContentProvider implements IStructuredContentProvider {

	private State state;

	public CityComboContentProvider() {
		super();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public void dispose() {
		this.state = null;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(state != null) {
			RefdataServices refdataServices = (RefdataServices) inputElement;
			return refdataServices.getCitiesByState(state).toArray();
		}
		return Collections.EMPTY_LIST.toArray();
	}

}
