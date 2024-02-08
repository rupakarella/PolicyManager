package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.AddressRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class AddressServiceImp implements IAddressService{
	@Autowired
	AddressRepository addressRepo;
	@Autowired
	UsersRepository usersRepo;
	@Override
	public Address createAddress(AddressDTO addressDTO) {
		Address address=new Address();
		address.setAddressId(addressDTO.getAddressId());
		address.setAddressLine(addressDTO.getAddressLine());
		address.setCity(addressDTO.getCity());
		address.setCityPincode(addressDTO.getCityPincode());
		address.setState(addressDTO.getState());
		
		Users user = addressDTO.getUsers();
		address.setUsers(addressDTO.getUsers());
	    user.setAddress(address); 
	    usersRepo.save(user);
		return addressRepo.save(address);
	}

	@Override
	public Address updateAddress(AddressDTO addressDTO) {
	    Optional<Address> optionalAddress = addressRepo.findById(addressDTO.getAddressId());
	    if (optionalAddress.isPresent()) {
	        Address address = optionalAddress.get();
	        address.setAddressLine(addressDTO.getAddressLine());
	        address.setCity(addressDTO.getCity());
	        address.setState(addressDTO.getState());
	        address.setCityPincode(addressDTO.getCityPincode());
	     
	        return addressRepo.save(address);
	    } 
	    else {
	    	return null;
	    }
	}

	@Override
	public String deleteByAddressId(long addressId) {
		addressRepo.deleteById(addressId);
		return "record deleted";
	}

	@Override
	public AddressDTO getByAddressId(long addressId) {	
		Optional<Address> optional = addressRepo.findById(addressId); 
		Address address = null;
		AddressDTO addressDTO=new AddressDTO();
		if (optional.isPresent()) {
	        address = optional.get();
	        if (address != null) {
	            addressDTO.setAddressId(address.getAddressId());
	            addressDTO.setAddressLine(address.getAddressLine());
	            addressDTO.setCity(address.getCity());
	            addressDTO.setState(address.getState());
	            addressDTO.setCityPincode(address.getCityPincode());
	        }
	    }
	    
	    return addressDTO;
	}

	@Override
	public List<Address> getByState(String state) {
		
		return addressRepo.findByState(state);
	}

	@Override
	public List<Address> getByCity(String city) {
		
		return addressRepo.findByCity(city);
	}

	@Override
	public List<Address> getAllAddress() {
		
		return addressRepo.findAll();
	}

}
