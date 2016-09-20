package com.bistro.sagg.core.services;

import com.bistro.sagg.core.spring.SaggApplicationContext;

public class SaggServiceLocator {

	public static EmployeeServices getEmployeeServices() {
		return SaggApplicationContext.getApplicationContext().getBean("employeeServices", EmployeeServices.class);
	}

	public static FranchiseServices getFranchiseServices() {
		return SaggApplicationContext.getApplicationContext().getBean("franchiseServices", FranchiseServices.class);
	}

	public static RefdataServices getRefdataServices() {
		return SaggApplicationContext.getApplicationContext().getBean("refdataServices", RefdataServices.class);
	}

}
