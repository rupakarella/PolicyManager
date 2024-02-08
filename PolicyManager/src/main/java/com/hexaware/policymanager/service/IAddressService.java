package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;

public interface IAddressService {
    public Address createAddress(AddressDTO addressDTO);
    public Address updateAddress(AddressDTO addressDTO);
    public String deleteByAddressId(long addressId);
    public AddressDTO getByAddressId(long addressId);
    public List<Address> getByState(String state);
    public List<Address> getByCity(String city);
    public List<Address> getAllAddress();
	
}
