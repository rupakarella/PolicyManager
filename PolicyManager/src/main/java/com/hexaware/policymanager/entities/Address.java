package com.hexaware.policymanager.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Addresses")
public class Address {

	@Id
	@Column(name = "addressId")
	private long addressId;

	@Column(name = "addressLine")
	@NotEmpty
	private String addressLine;
	
	
	@NotEmpty
	private String city;

	@Column(name = "cityPincode")
	private int cityPincode;
    
	@NotEmpty
	@Column(name = "state")
	private String state;

	@OneToOne(mappedBy = "address")
	private List<Users> users;

	public Address() {
		super();

	}

	public Address(long addressId, String addressLine,String city, int cityPincode, String state, List<Users> users) {
		super();
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.cityPincode = cityPincode;
		this.state = state;
		this.users = users;
		this.city = city;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressLine=" + addressLine + ", city=" + city + ", cityPincode="
				+ cityPincode + ", state=" + state + ", users=" + users + "]";
	}

	
	

}
