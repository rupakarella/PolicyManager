package com.hexaware.policymanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressSequenceGenerator")
	@SequenceGenerator(name = "AddressSequenceGenerator", sequenceName = "AddressSeq", allocationSize = 1,initialValue =70000)
	private long addressId;

	@NotBlank(message = "addressLine should not be blank")
	private String addressLine;

	@NotBlank(message = "city should not be blank")
	private String city;

	@NotNull(message = "cityPincode should not be blank")
	private int cityPincode;

	@NotBlank(message = "state should not be blank")
	private String state;

	@OneToOne(mappedBy = "address", cascade = CascadeType.REMOVE)
	@JsonBackReference(value = "Users-Address")
	private Users users;

	public Address() {
		super();
	}

	public Address(long addressId, @NotBlank String addressLine, @NotBlank String city, @NotNull int cityPincode,
			@NotBlank String state, Users users) {
		super();
		this.addressId = addressId;
		this.addressLine = addressLine;
		this.city = city;
		this.cityPincode = cityPincode;
		this.state = state;
		this.users = users;
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

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressLine=" + addressLine + ", city=" + city + ", cityPincode="
				+ cityPincode + ", state=" + state + "]";
	}

}