package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.AddressNotFoundException;
import com.hexaware.policymanager.repository.AddressRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressServiceImp implements IAddressService {

	Logger logger = LoggerFactory.getLogger(AddressServiceImp.class);

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	UsersRepository usersRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Address createAddress(AddressDTO addressDTO) {
		try {
			Address address = new Address();
			address.setAddressId(addressDTO.getAddressId());
			address.setAddressLine(addressDTO.getAddressLine());
			address.setCity(addressDTO.getCity());
			address.setCityPincode(addressDTO.getCityPincode());
			address.setState(addressDTO.getState());

			Users user = addressDTO.getUsers();
			user.setPassword(bCryptPasswordEncoder.encode(addressDTO.getUsers().getPassword()));
			address.setUsers(addressDTO.getUsers());
			user.setAddress(address);
			usersRepo.save(user);

			Address createdAddress = addressRepo.save(address);
			logger.info("Address created succesfully: {}", createdAddress);
			return createdAddress;
		} catch (Exception e) {
			logger.error("Error creating address", e);
			throw new RuntimeException("Error creating address", e);
		}

	}

	@Override
	public Address updateAddress(AddressDTO addressDTO) {
		try {
			Optional<Address> optionalAddress = addressRepo.findById(addressDTO.getAddressId());
			if (optionalAddress.isPresent()) {
				Address address = optionalAddress.get();
				address.setAddressLine(addressDTO.getAddressLine());
				address.setCity(addressDTO.getCity());
				address.setState(addressDTO.getState());
				address.setCityPincode(addressDTO.getCityPincode());

				Address updatedAddress = addressRepo.save(address);
				logger.info("Address updated successfully: {}", updatedAddress);
				return updatedAddress;
			} else {
				throw new AddressNotFoundException("Address not found with ID: " + addressDTO.getAddressId());
			}
		} catch (Exception e) {
			logger.error("Error updating address", e);
			throw new RuntimeException("Error updating address", e);
		}

	}

	@Override
	public String deleteByAddressId(long addressId) {
		try {
			if (!addressRepo.existsById(addressId)) {
				throw new AddressNotFoundException("Address not found with ID: " + addressId);
			}
			addressRepo.deleteById(addressId);
			logger.info("Address deleted successfully with ID: {}", addressId);
			return "record deleted";
		} catch (Exception e) {
			logger.error("Error deleting address", e);
			throw new RuntimeException("Error deleting address", e);
		}
	}

	@Override
	public AddressDTO getByAddressId(long addressId) {
		try {
			Optional<Address> optional = addressRepo.findById(addressId);
			if (optional.isPresent()) {
				Address address = optional.get();
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setAddressId(address.getAddressId());
				addressDTO.setAddressLine(address.getAddressLine());
				addressDTO.setCity(address.getCity());
				addressDTO.setState(address.getState());
				addressDTO.setCityPincode(address.getCityPincode());
				return addressDTO;
			} else {
				throw new AddressNotFoundException("Address not found with ID: " + addressId);
			}
		} catch (Exception e) {
			logger.error("Error fetching address by ID", e);
			throw new RuntimeException("Error fetching address by ID", e);
		}
	}

	@Override
	public List<Address> getByState(String state) {
		try {
			List<Address> addresses = addressRepo.findByState(state);
			if (addresses.isEmpty()) {
				logger.error("No addresses found for state '{}'", state);
				throw new AddressNotFoundException("No addresses found for state: " + state);
			}
			logger.info("Retrieved addresses by state '{}' successfully: {}", state, addresses);
			return addresses;
		} catch (Exception e) {
			logger.error("Error getting addresses by state", e);
			throw new RuntimeException("Error getting addresses by state", e);
		}
	}

	@Override
	public List<Address> getByCity(String city) {
		try {
			List<Address> addresses = addressRepo.findByCity(city);
			if (addresses.isEmpty()) {
				logger.error("No addresses found for city '{}'", city);
				throw new AddressNotFoundException("No addresses found for city: " + city);
			}
			logger.info("Retrieved addresses by city '{}' successfully: {}", city, addresses);
			return addresses;
		} catch (Exception e) {
			logger.error("Error getting addresses by city", e);
			throw new RuntimeException("Error getting addresses by city", e);
		}
	}

	@Override
	public List<Address> getAllAddress() {
		try {
			List<Address> addresses = addressRepo.findAll();
			logger.info("Retrieved all addresses successfully: {}", addresses);
			return addresses;
		} catch (Exception e) {
			logger.error("Error getting all addresses", e);
			throw new RuntimeException("Error getting all addresses", e);
		}
	}

}