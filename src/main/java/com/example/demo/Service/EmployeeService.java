package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.pagination.TopLimitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.vo.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public void addEmp(Employee employee) {
		//a.add(employee);
		if (employee.getEmpId() != null && !employee.getEmpId().isEmpty()) {
			employeeRepo.save(employee);
		}
		else {
			throw new BadRequestException("Emp Id is null");
		}
	}
	
	public void updateEmp(String id, Employee employee) {
		if (id != null && employee != null) {
			employeeRepo.save(employee);
		}
		else {
			throw new BadRequestException("Input data mismatch");
		}
	}
	
	public void deleteEMp(String id) {
		Optional<Employee> findById = employeeRepo.findById(id);
		if (findById.isPresent()) {
			employeeRepo.deleteById(id);
		}
		else {
			throw new EmployeeNotFoundException("Employee Not Found");
		}
	}
	
	public List<Employee> getEmpDetails() {
		//return a;
		List<Employee> employees = new ArrayList<Employee>(); 
		employeeRepo.findAll().forEach(employees::add);
		return employees;
	}
	
	public Optional<Employee> getEmployeeById(String id) {
		return employeeRepo.findById(id);
	}
	
	public boolean isLoginSuccess(String username, String password) {
		boolean isLoginSuccess = false;
		if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			Optional<Employee> findByUsername = employeeRepo.findByName(username);
			if (findByUsername.isPresent()) {
				if (password.equals(findByUsername.get().getPassword())) {
					isLoginSuccess = true;
				}
			}
		}
		if (!isLoginSuccess) {
			throw new BadRequestException("Invalid Username and Password.");
		}
		return isLoginSuccess;
	}
}
