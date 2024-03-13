package com.hexaware.policymanager.dto;

import java.time.LocalDate;
import java.util.List;

import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.entities.ProfilePicture;
import com.hexaware.policymanager.entities.UserPolicies;

public class UsersDTO {
	private long userId;
	private String emailAddress;
	private String contactNumber;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String panNumber;
	private String employerType;
	private String employerName;
	private double salary;
	private String userType;
	private Address address;
	private List<UserPolicies> userPolicies;
	private ProfilePicture profile;

	public UsersDTO() {
		super();
	}

	public UsersDTO(long userId, String emailAddress, String contactNumber, String password, String firstName,
			String lastName, LocalDate dateOfBirth, String panNumber, String employerType, String employerName,
			double salary, String userType, Address address, List<UserPolicies> userPolicies, ProfilePicture profile) {
		super();
		this.userId = userId;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.panNumber = panNumber;
		this.employerType = employerType;
		this.employerName = employerName;
		this.salary = salary;
		this.userType = userType;
		this.address = address;
		this.userPolicies = userPolicies;
		this.profile=profile;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
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
	

	public ProfilePicture getProfile() {
		return profile;
	}

	public void setProfile(ProfilePicture profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "UsersDTO [userId=" + userId + ", emailAddress=" + emailAddress + ", contactNumber=" + contactNumber
				+ ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", panNumber=" + panNumber + ", employerType=" + employerType + ", employerName="
				+ employerName + ", salary=" + salary + ", userType=" + userType + ", address=" + address
				+ ", userPolicies=" + userPolicies + "]";
	}


    
}