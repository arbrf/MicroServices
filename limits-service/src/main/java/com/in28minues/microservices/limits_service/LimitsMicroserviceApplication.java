package com.in28minues.microservices.limits_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LimitsMicroserviceApplication {

	public static void main(String[] args) {

		SpringApplication.run(LimitsMicroserviceApplication.class, args);
		System.out.println("Limits Service Started");
	}

}
