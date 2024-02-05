package com.hexaware.policymanager.dto;

import java.sql.Date;
import java.util.List;

import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.entities.UserPolicies;

public class UsersDTO {
	private long userId;
	private String emailAddress;
	private String contactNo;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String panNo;
	private String employerType;
	private String employerName;
	private double salary;
	private String userType;
	private Address address;
	private List<UserPolicies> userPolicies;
	public UsersDTO() {
		super();
	}
	
	public UsersDTO(long userId, String emailAddress, String contactNo, String password, String firstName,
			String lastName, Date dateOfBirth, String panNo, String employerType, String employerName, double salary,
			String userType, Address address, List<UserPolicies> userPolicies) {
		super();
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.contactNo = contactNo;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.panNo = panNo;
		this.employerType = employerType;
		this.employerName = employerName;
		this.salary = salary;
		this.userType = userType;
		this.address = address;
		this.userPolicies = userPolicies;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getEmployerType() {
		return employerType;
	}
	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<UserPolicies> getUserPolicies() {
		return userPolicies;
	}

	public void setUserPolicies(List<UserPolicies> userPolicies) {
		this.userPolicies = userPolicies;
	}

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", emailAddress=" + emailAddress + ", contactNo=" + contactNo
				+ ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", panNo=" + panNo + ", employerType=" + employerType + ", employerName=" + employerName
				+ ", salary=" + salary + ", userType=" + userType + ", address=" + address + ", userPolicies="
				+ userPolicies + "]";
	}

	
}