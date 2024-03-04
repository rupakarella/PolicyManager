package com.hexaware.policymanager.dto;

public class LoginResponse {

	private long userId;
	private String userType;
	private String token;
	private String userName;
	private String employerType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public String getEmployerType() {
		return employerType;
	}

	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}

	
	public LoginResponse(long userId, String userType, String token, String userName, String employerType) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.token = token;
		this.userName = userName;
		this.employerType = employerType;
	}

	public LoginResponse() {
		super();
	}

	@Override
	public String toString() {
		return "LoginResponse [userId=" + userId + ", userType=" + userType + ", token=" + token + "]";
	}

}