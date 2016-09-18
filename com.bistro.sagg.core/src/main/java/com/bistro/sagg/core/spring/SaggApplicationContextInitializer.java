package com.bistro.sagg.core.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SaggApplicationContextInitializer {

	public static void start() {
		new ClassPathXmlApplicationContext("/application-context.xml");
	}

}
