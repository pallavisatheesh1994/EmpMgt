package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.vo.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, String> {

	@Query("SELECT e FROM Employee e WHERE e.username=:name")
	Optional<Employee> findByName(@Param(value = "name") String name);
}
