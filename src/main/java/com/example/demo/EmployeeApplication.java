package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeeApplication {

	/*@Autowired
	private EmpRepo empRepo;*/
	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
}
