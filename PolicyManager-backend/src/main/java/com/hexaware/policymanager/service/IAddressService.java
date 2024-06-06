package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.exception.AddressNotFoundException;
import com.hexaware.policymanager.exception.DuplicateUserException;

public interface IAddressService {
	public Address createAddress(AddressDTO addressDTO) throws DuplicateUserException;

	public Address updateAddress(AddressDTO addressDTO) throws AddressNotFoundException;

	public String deleteByAddressId(long addressId) throws AddressNotFoundException;

	public AddressDTO getByAddressId(long addressId) throws AddressNotFoundException;

	public List<Address> getByState(String state) throws AddressNotFoundException;

	public List<Address> getByCity(String city) throws AddressNotFoundException;

	public List<Address> getAllAddress();

}