package com.hexaware.policymanager.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.service.IUsersService;

@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

	@Autowired
	IUsersService services;

	@PostMapping(value = "/register")
	public Users registerUser(@RequestBody UsersDTO userDTO) {
		return services.registerUser(userDTO);
	}

	@PutMapping(value = "/update")
	public Users updateUser(@RequestBody UsersDTO userDTO) {
		return services.updateUser(userDTO);
	}

	@DeleteMapping(value = "/delete/{userId}")
	public void deleteByUserId(@PathVariable long userId) {
		services.deleteByUserId(userId);
	}

	@GetMapping(value = "/get/id/{userId}")
	public UsersDTO getUserById(@PathVariable long userId) {
		return services.getById(userId);
	}
	
	@GetMapping(value="/get/email/{email}")
	public Users getUserPolicyByEmail(@PathVariable String email)
	{
		return services.getUserByEmail(email);
	}
	
	@GetMapping(value="/get/type/{userType}")
	public List<Users> getUserByUserType(@PathVariable String userType)
	{
		return services.getUserByUserType(userType);
	}
	
	@GetMapping(value="/get/contactno/{contactno}")
	public Users getUserByConatctno(@PathVariable String contactNo)
	{
		return services.getUserBycontactNo(contactNo);
	}
	
	@GetMapping(value="/getall")
	public List<Users> getAllUsers()
	{
		return services.getAllUsers();
	}

}