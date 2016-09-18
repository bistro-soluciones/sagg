package com.bistro.sagg.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SaggApplicationContextProvider implements ApplicationContextAware {

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// Wiring the ApplicationContext into a static method
		SaggApplicationContext.setApplicationContext(applicationContext);
	}

}
