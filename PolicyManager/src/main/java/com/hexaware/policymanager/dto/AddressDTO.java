package com.hexaware.policymanager.dto;

import com.hexaware.policymanager.entities.Users;

public class AddressDTO {
	private long addressId;
	private String addressLine;
	private String city;
	private int cityPincode;
	private String state;
	private Users users;
	private long userId;
	public AddressDTO() {
		super();
	}
	
	public AddressDTO(long addressId, String addressLine, String city, int cityPincode, String state,long userId) {
		super();
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.city = city;
		this.cityPincode = cityPincode;
		this.state = state;
		this.userId = userId;
	}

	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCityPincode() {
		return cityPincode;
	}
	public void setCityPincode(int cityPincode) {
		this.cityPincode = cityPincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
		
	
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "AddressDTO [addressId=" + addressId + ", addressLine=" + addressLine + ", city=" + city
				+ ", cityPincode=" + cityPincode + ", state=" + state + ", userId=" + userId + "]";
	}

		
}