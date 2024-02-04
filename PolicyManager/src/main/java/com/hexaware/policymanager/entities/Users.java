package com.hexaware.policymanager.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Users")
public class Users {

	@Id
	@Column(name = "UserID")
	private long userId;

	@Email
	@Column(name = "EmailAddress")
	private String emailAddress;

	//@Pattern(regexp="^[+]?[6789]\\d{9,14}$\r\n")
	@Column(name = "ContactNo")
	private String contactNo;

	@NotEmpty
	@Column(name = "Password")
	private String password;
    
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@Column(name = "FirstName")
	private String firstName;

	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z\\s]+$")
	@Column(name = "LastName")
	private String lastName;

	@Column(name = "DateOfBirth")
	private Date dateOfBirth;

	@Column(name = "PANNo")
	private String panNo;

	@Column(name = "EmployerType")
	private String employerType;

	@NotEmpty
	@Column(name = "EmployerName")
	private String employerName;

	@Column(name = "Salary")
	private Double salary;

	
	@Pattern(regexp = "^(Admin|User)$")
	@Column(name = "UserType")
	private String userType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserPolicies> userPolicies;

	public Users() {
		super();

	}

	public Users(int userId, String emailAddress, String contactNo, String password, String firstName, String lastName,
			Date dateOfBirth, String panNo, String employerType, String employerName, Double salary, String userType,
			Address address, List<UserPolicies> userPolicies) {
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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
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
		return "Users [userId=" + userId + ", emailAddress=" + emailAddress + ", contactNo=" + contactNo + ", password="
				+ password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", panNo=" + panNo + ", employerType=" + employerType + ", employerName=" + employerName + ", salary="
				+ salary + ", userType=" + userType + ", address=" + address + ", user_Policies=" + userPolicies + "]";
	}
	

}