package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hexaware.policymanager.service.IAddressService;

@RestController
@RequestMapping("/api/address")
public class AddressRestController {
	@Autowired
	IAddressService service;
	@PostMapping("/add")
	public Address createAddress(@RequestBody AddressDTO addressDTO)
	{
		return service.createAddress(addressDTO);
	}
	@PutMapping("/update")
	public Address updateAddress(@RequestBody AddressDTO addressDTO)
	{
		return service.updateAddress(addressDTO);
	}
	@DeleteMapping("/delete/{addressId}")
	public void deleteByAddressId(@PathVariable long addressId)
	{
		service.deleteByAddressId(addressId);
	}
	@GetMapping("/getbyid/{addressId}")
	public AddressDTO getbyAddressId(@PathVariable long addressId)
	{
		return service.getbyAddressId(addressId);
	}
	@GetMapping("/get/state/{state}")
	public List<Address> getAddressByState(@PathVariable String state)
	{
		return service.getByState(state);
		
	}
	@GetMapping("/get/city/{city}")
	public List<Address> getAddressByCity(@PathVariable String city)
	{
		return service.getByCity(city);
		
	}
	@GetMapping("/getall")
	public List<Address> getAllAddress()
	{
		return service.getAllAddress();
	}
}