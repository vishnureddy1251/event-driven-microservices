package com.eventdrivenmicroservices.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventDrivenMicroservicesApplication {

	public static void main(String[] args) {

		System.out.println("========================================");
		System.out.println("Starting Event-Driven Microservices");
		System.out.println("Learning Project");
		System.out.println("========================================\n");

		SpringApplication.run(EventDrivenMicroservicesApplication.class, args);

		System.out.println("\n Application started successfully!");
		System.out.println(" API: http://localhost:8080/api/grades");
		System.out.println(" Endpoints:");
		System.out.println("   GET  http://localhost:8080/api/grades");
		System.out.println("   POST http://localhost:8080/api/grades/submit");
		System.out.println("   GET  http://localhost:8080/api/grades/students");
		System.out.println("   POST http://localhost:8080/api/grades/process-all");
		System.out.println("========================================\n");
	}

}
