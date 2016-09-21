package com.bistro.sagg.core.services;

import java.util.Map;

import com.bistro.sagg.core.spring.SaggApplicationContext;

public class SaggServiceLocator {

	private static SaggServiceLocator instance;

	private Map<String, ISaggService> services;

	public static synchronized SaggServiceLocator getInstance() {
		if (instance == null) {
			instance = SaggApplicationContext.getApplicationContext().getBean(SaggServiceLocator.class);
		}
		return instance;
	}

	public Map<String, ISaggService> getServices() {
		return services;
	}

	public void setServices(Map<String, ISaggService> services) {
		this.services = services;
	}

	public ISaggService lookup(String service) {
		return this.services.get(service);
	}

}
