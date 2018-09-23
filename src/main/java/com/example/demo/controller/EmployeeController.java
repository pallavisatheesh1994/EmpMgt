package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.EmployeeService;
import com.example.demo.vo.Employee;
import com.example.demo.vo.EmployeeResponse;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empSerive;
	
	@Cacheable(value = "employees")
	@RequestMapping("/EmpMgt/getAllEmpDetails")
	public List<Employee> getAllEmpDetails() {
		System.out.println("getAllEmpDetails - getting called");
		return empSerive.getEmpDetails();
	}
	
	@Cacheable(value = "employees", key = "#p0")
	@RequestMapping("/EmpMgt/getByEmpId/{id}")
	public Optional<Employee> getEmpDetails(@PathVariable String id) {
		System.out.println("getting called" + id);
		return empSerive.getEmployeeById(id);
	}
	
	@CachePut(value = "employees", key = "#result.employee.empId")
	@RequestMapping(method = RequestMethod.POST, value = "/EmpMgt/addEmpDetails")
	public EmployeeResponse addEmpDetails(@RequestBody Employee employee) {
		EmployeeResponse response = new EmployeeResponse();
		System.out.println("addEmpDetails - getting called");
		empSerive.addEmp(employee);
		response.setEmployee(employee);
		response.setStatus("SUCCESS");
		response.setStatusCode(HttpStatus.OK.toString());
		response.setMessage("Data added successfully");
		return response;
	}
	
	@CachePut(value = "employees", key = "#p0")
	@RequestMapping(method = RequestMethod.PUT, value = "/EmpMgt/addEmp/{id}")
	public EmployeeResponse updateEmpDetails(@PathVariable("id") String id, @RequestBody (required=false) Employee employee) {
		EmployeeResponse response = new EmployeeResponse();
		System.out.println("updateEmpDetails - getting called" +id);
		empSerive.updateEmp(id, employee);
		response.setEmployee(employee);
		response.setStatus("SUCCESS");
		response.setStatusCode(HttpStatus.OK.toString());
		response.setMessage("Employee data inserted successfully.");
		return response;
	}
	
	@CacheEvict(value = "employees", allEntries=true)
	@RequestMapping(method = RequestMethod.DELETE, value = "/EmpMgt/deleteEmp/{id}")
	public EmployeeResponse deleteEmpDetails(@PathVariable("id") String id) {
		EmployeeResponse response = new EmployeeResponse();
		System.out.println("deleteEmpDetails - getting called" +id);
		empSerive.deleteEMp(id);
		response.setStatus("SUCCESS");
		response.setStatusCode(HttpStatus.OK.toString());
		response.setMessage("Employee data deleted successfully.");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/EmpMgt/checkLogin")
	public EmployeeResponse validateEmp(@RequestBody Employee employee) {
		EmployeeResponse response = new EmployeeResponse();
		empSerive.isLoginSuccess(employee.getUsername(), employee.getPassword());
		response.setEmployee(employee);
		response.setStatus("SUCCESS");
		response.setStatusCode(HttpStatus.OK.toString());
		response.setMessage("Employee has authenticated successfully.");
		return response;
	}
	
}
