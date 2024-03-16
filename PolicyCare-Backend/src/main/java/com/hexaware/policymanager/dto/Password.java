package com.hexaware.policymanager.dto;

public class Password{
	
	private String email;
	private String newPassword;
	
	public Password() {
		super();
	}
	public Password( String email,String newPassword) {
		super();
		
		this.email = email;
		this.newPassword= newPassword;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}