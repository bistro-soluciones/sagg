package com.bistro.sagg.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bistro.sagg.core.model.Employee;
import com.bistro.sagg.core.repository.EmployeeRepository;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(EmployeeRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Employee("Jack", "11.215.122-7"));
			repository.save(new Employee("Chloe", "7.326.333-4"));
			repository.save(new Employee("Kim", "15.235.323-7"));
			repository.save(new Employee("David", "24.555.154-7"));
			repository.save(new Employee("Michelle", "12.1214.989-k"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Employee employee : repository.findAll()) {
				log.info(employee.getName() + " " + employee.getPersonId());
			}
			log.info("");
		};
	}
}
