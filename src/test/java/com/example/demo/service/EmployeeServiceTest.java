package com.example.demo.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Service.EmployeeService;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repo.EmployeeRepo;
import com.example.demo.vo.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@Mock
	EmployeeRepo employeeRepo;
	@InjectMocks
	EmployeeService empService;
	
	
	@Test
	public void testAddEmp() {
		Employee employee = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(employee);
		empService.addEmp(employee);
		Assert.assertEquals("6", employee.getEmpId());
	}
	
	@Test
	public void testAddEmpWhenIdIsNull() {
		Employee employee = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		try {
			empService.addEmp(employee);
		}
		catch (BadRequestException ex) {
			Assert.assertEquals("Emp Id is null", ex.getMessage());
		}
	}
	
	@Test
	public void testUpdateEmp() {
		Employee employee = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(employee);
		empService.updateEmp("6", employee);
		Assert.assertEquals("6", employee.getEmpId());
	}

	@Test
	public void testUpdateEmpWhenIdIsNull() {
		Employee employee = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Mockito.when(employeeRepo.save(Mockito.any(Employee.class))).thenReturn(employee);
		try {
			empService.updateEmp("6", employee);
		}
		catch (BadRequestException ex) {
			Assert.assertEquals("Input data mismatch", ex.getMessage());
		}
	}
	
	@Test
	public void testDeleteEmp() {
		Employee employeeOne = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Optional<Employee> emp = Optional.of(employeeOne);
		Mockito.when(employeeRepo.findById(Mockito.anyString())).thenReturn(emp);
		Mockito.doNothing().when(employeeRepo).deleteById(Mockito.anyString());
		empService.deleteEMp("6");
	}
	
	@Test
	public void testDeleteWhenEmpNotPresent() {
		Employee employeeOne = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("7").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Optional<Employee> emp = Optional.of(employeeOne);
		Mockito.when(employeeRepo.findById(Mockito.anyString())).thenReturn(emp);
		try {
			empService.deleteEMp("6");
		}
		catch (EmployeeNotFoundException ex) {
			Assert.assertEquals("Employee Not Found", ex.getMessage());
		}
	}
	
	@Test
	public void testGetEmpDetails() {
		Employee employeeOne = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Employee employeeTwo = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("7").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		List<Employee> employeeList = new ArrayList<Employee>();
		employeeList.add(employeeOne);
		employeeList.add(employeeTwo);
		Mockito.when(employeeRepo.findAll()).thenReturn(employeeList);
		empService.getEmpDetails();
		Assert.assertEquals(2, employeeList.size());
		
	}
	
	@Test
	public void testGetEmployeeById() {
		Employee employeeOne = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Optional<Employee> emp = Optional.of(employeeOne);
		Mockito.when(employeeRepo.findById(Mockito.anyString())).thenReturn(emp);
		empService.getEmployeeById("6");
		Assert.assertTrue(emp.isPresent());
	}
	
	@Test
	public void tesIsLoginSuccess() {
		Employee employeeOne = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		Optional<Employee> emp = Optional.of(employeeOne);
		Mockito.when(employeeRepo.findByName(Mockito.anyString())).thenReturn(emp);
		boolean isValid = empService.isLoginSuccess("A", "aa");
		Assert.assertTrue(isValid);
	}
	
}
