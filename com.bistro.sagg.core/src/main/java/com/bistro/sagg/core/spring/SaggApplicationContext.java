package com.bistro.sagg.core.spring;

import org.springframework.context.ApplicationContext;

public class SaggApplicationContext {

	private static ApplicationContext appContext;

	/**
	 * Injected from the class "ApplicationContextProvider" which is
	 * automatically loaded during Spring-Initialization.
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		appContext = applicationContext;
	}

	/**
	 * Get access to the Spring ApplicationContext from everywhere in your
	 * Application.
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return appContext;
	}

}
