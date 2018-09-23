package com.example.demo.vo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable {

	private String username;
	
	private String password;
	
	private String fullName;
	
	private String emailID;
	
	private String dob;
	
	private String gender;
	
	private String securityQuestion;
	
	private String securityAnswer;
	
	@Id
	private String empId;
	
	//private List<Employee> employeeList;
	
	public Employee() {
		
	}
	
	public Employee(String username, String password, String fullName,
			String emailID, String dob, String gender, String securityQuestion,
			String securityAnswer, String empId) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.emailID = emailID;
		this.dob = dob;
		this.gender = gender;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.empId = empId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	
}
