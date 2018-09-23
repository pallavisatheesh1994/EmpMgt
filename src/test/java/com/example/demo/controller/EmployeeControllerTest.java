package com.example.demo.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.Service.EmployeeService;
import com.example.demo.service.EmployeeBuilder;
import com.example.demo.vo.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	private MockMvc mockMvc;
	@Mock
	EmployeeService empService;
	@InjectMocks
	EmployeeController empController;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
	}
	
	@Test
	public void testAddEmp() {
		String inputJson = "{\"username\": \"Paa\",\"password\":\"aaa\",\"fullName\": \"PALLAVI\","
	            + "\"emailID\": \"aaa@gmail.com\",\"dob\": \"15-02-1994\",\"gender\": \"F\","
	            + "\"securityQuestion\":\"what is your name?\",\"securityAnswer\": \"aaww\",\"empId\": \"6\"}";
		Mockito.doNothing().when(empService).addEmp(Mockito.any(Employee.class));
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/EmpMgt/addEmpDetails")
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			Assert.fail();
		}

	}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		Employee employee = new EmployeeBuilder().addUsername("A").addPassword("aa")
				.addDOB("a").addEmailID("a").addEmpId("6").addFullName("AAAA").addGender("F")
				.addSA("sA").addSQ("sQ").build();
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(employee);
		Mockito.when(empService.getEmpDetails()).thenReturn(empList);
		mockMvc.perform(MockMvcRequestBuilders.get("/EmpMgt/getAllEmpDetails")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("A")));

	}
	
	@Test
	public void testUpdateEmp() {
		String inputJson = "{\"username\": \"Paa\",\"password\":\"aaa\",\"fullName\": \"PALLAVI\","
	            + "\"emailID\": \"aaa@gmail.com\",\"dob\": \"15-02-1994\",\"gender\": \"F\","
	            + "\"securityQuestion\":\"what is your name?\",\"securityAnswer\": \"aaww\",\"empId\": \"6\"}";
		Mockito.doNothing().when(empService).updateEmp(Mockito.anyString(),Mockito.any(Employee.class));
		try {
			mockMvc.perform(MockMvcRequestBuilders.put("/EmpMgt/addEmp/6")
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(inputJson)).andExpect(MockMvcResultMatchers.status().isOk());
		} 
		catch (Exception e) {
			Assert.fail();
		}

	}
	
	@Test
	public void testDeleteEmp() {
		String inputJson = "{\"username\": \"Paa\",\"password\":\"aaa\",\"fullName\": \"PALLAVI\","
	            + "\"emailID\": \"aaa@gmail.com\",\"dob\": \"15-02-1994\",\"gender\": \"F\","
	            + "\"securityQuestion\":\"what is your name?\",\"securityAnswer\": \"aaww\",\"empId\": \"6\"}";
		Mockito.doNothing().when(empService).deleteEMp(Mockito.anyString());
		try {
			mockMvc.perform(MockMvcRequestBuilders.delete("/EmpMgt/deleteEmp/6")
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(inputJson))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} 
		catch (Exception e) {
			Assert.fail();
		}

	}

}
