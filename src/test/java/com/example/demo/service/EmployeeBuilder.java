package com.example.demo.service;

import com.example.demo.vo.Employee;

public class EmployeeBuilder {
	
	Employee employee = new Employee();
	
	public Employee build() {
		return employee;
	}

	public EmployeeBuilder() {
		
	}
	
	public EmployeeBuilder addUsername(String name) {
		employee.setUsername(name);
		return this;
	}
	public EmployeeBuilder addPassword(String password) {
		employee.setPassword(password);
		return this;
	}
	public EmployeeBuilder addFullName(String fullName) {
		employee.setFullName(fullName);
		return this;
	}
	public EmployeeBuilder addEmailID(String emailID) {
		employee.setEmailID(emailID);
		return this;
	}
	public EmployeeBuilder addDOB(String dob) {
		employee.setDob(dob);
		return this;
	}
	public EmployeeBuilder addGender(String gender) {
		employee.setGender(gender);
		return this;
	}
	public EmployeeBuilder addSQ(String sQ) {
		employee.setSecurityQuestion(sQ);
		return this;
	}
	public EmployeeBuilder addSA(String sA) {
		employee.setSecurityAnswer(sA);
		return this;
	}
	public EmployeeBuilder addEmpId(String empId) {
		employee.setEmpId(empId);
		return this;
	}
}
