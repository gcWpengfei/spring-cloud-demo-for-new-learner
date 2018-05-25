package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class WpfHystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(WpfHystrixTurbineApplication.class, args);
	}
}
