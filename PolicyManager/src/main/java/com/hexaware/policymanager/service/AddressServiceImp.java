package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.repository.AddressRepository;

public class AddressServiceImp implements IAddressService{
	@Autowired
	AddressRepository addressRepo;
	@Override
	public Address createAddress(AddressDTO addressDTO) {
		Address address=new Address();
		address.setAddressId(addressDTO.getAddressId());
		address.setAddressLine(addressDTO.getAddressLine());
		address.setCity(addressDTO.getCity());
		address.setCityPincode(address.getCityPincode());
		address.setState(addressDTO.getState());
		return addressRepo.save(address);
	}

	@Override
	public Address updateAddress(AddressDTO addressDTO) {
		Address address = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setAddressLine(addressDTO.getAddressLine());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCityPincode(addressDTO.getCityPincode());
        return addressRepo.save(address);
	}

	@Override
	public String deleteByAddressId(long addressId) {
		addressRepo.deleteById(addressId);
		return "record deleted";
	}

	@Override
	public Address getbyAddressId(long addressId) {	
		Optional<Address> optional = addressRepo.findById(addressId); 
		Address address = null;
		  if (optional.isPresent()) {
		  address = optional.get(); }
		  return address;
	}

	@Override
	public List<Address> getByState(String state) {
		
		return addressRepo.getByState(state);
	}

	@Override
	public List<Address> getByCity(String city) {
		
		return addressRepo.getByCity(city);
	}

	@Override
	public List<Address> getAllAddress() {
		
		return addressRepo.findAll();
	}

}
