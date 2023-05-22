package com.healthCheckApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthCheckApplication {

	public static void main(String[] args) {
		System.out.println("HealthApplication deployed");

		SpringApplication.run(HealthCheckApplication.class, args);
	}

}
