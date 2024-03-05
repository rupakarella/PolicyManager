package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.exception.AddressNotFoundException;
import com.hexaware.policymanager.exception.DuplicateUserException;
import com.hexaware.policymanager.service.IAddressService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/address")
public class AddressRestController {
	@Autowired
	IAddressService addressService;

	@PostMapping("/register")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public Address createAddress(@RequestBody AddressDTO addressDTO) throws DuplicateUserException {
		return addressService.createAddress(addressDTO);
	}

	@PutMapping("/update")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public Address updateAddress(@RequestBody AddressDTO addressDTO) throws AddressNotFoundException {
		return addressService.updateAddress(addressDTO);
	}

	@DeleteMapping("/delete/{addressId}")
	@PreAuthorize("hasAuthority('User')")
	public String deleteByAddressId(@PathVariable long addressId) throws AddressNotFoundException {
		return addressService.deleteByAddressId(addressId);
	}

	@GetMapping("/get-by-id/{addressId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public AddressDTO getByAddressId(@PathVariable long addressId) throws AddressNotFoundException {
		return addressService.getByAddressId(addressId);
	}

	@GetMapping("/get-by-state/{state}")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Address> getAddressByState(@PathVariable String state) throws AddressNotFoundException {
		return addressService.getByState(state);

	}

	@GetMapping("/get-by-city/{city}")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Address> getAddressByCity(@PathVariable String city) throws AddressNotFoundException {
		return addressService.getByCity(city);

	}

	@GetMapping("/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Address> getAllAddress() {
		return addressService.getAllAddress();
	}
}