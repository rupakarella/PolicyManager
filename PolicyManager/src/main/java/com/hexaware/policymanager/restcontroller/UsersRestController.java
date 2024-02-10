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

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.DuplicateUserException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.service.IUsersService;

@RestController
@RequestMapping(value = "/api/users")
public class UsersRestController {

	@Autowired
	IUsersService services;

	@PostMapping(value = "/register")
	public Users registerUser(@RequestBody UsersDTO userDTO)throws RuntimeException, DuplicateUserException  {
		
		return services.registerUser(userDTO);
	}

	@PutMapping(value = "/update")
	public Users updateUser(@RequestBody UsersDTO userDTO)throws RuntimeException  {
		return services.updateUser(userDTO);
	}

	@DeleteMapping(value = "/delete/{userId}")
	public String deleteByUserId(@PathVariable long userId) throws UserNotFoundException {
		return services.deleteByUserId(userId);
	}

	@GetMapping(value = "/get/id/{userId}")
	public UsersDTO getUserById(@PathVariable long userId) throws UserNotFoundException {
		return services.getById(userId);
	}
	
	@GetMapping(value="/get/email/{email}")
	public Users getUserPolicyByEmail(@PathVariable String email) throws UserNotFoundException
	{
		return services.getUserByEmail(email);
	}
	
	@GetMapping(value="/get/type/{userType}") 
	public List<Users> getUserByUserType(@PathVariable String userType)throws UserNotFoundException 
	{
		return services.getUserByUserType(userType);
	}
	
	@GetMapping(value="/get/contactnumber/{contactNumber}")
	public Users getUserByConatctno(@PathVariable String contactNumber) throws UserNotFoundException
	{
		return services.getUserBycontactNumber(contactNumber);
	}
	
	@GetMapping(value="/getall")
	public List<Users> getAllUsers()
	{
		return services.getAllUsers();
	}
	@GetMapping(value="/get-userType-by-emailAddress/{emailAddress}")
	public String findUserTypeByEmailAddress(@PathVariable String emailAddress) {
		
		return services.findUserTypeByEmailAddress(emailAddress);
	}
	@GetMapping(value="/get-userId-by-emailAddress/{emailAddress}")
	public long findUserIdByEmailAddress(@PathVariable String emailAddress) {
		
		return services.findUserIdByEmailAddress(emailAddress);
	}
	@GetMapping(value="/get-userName-by-emailAddress/{emailAddress}")
	public String findUserNameByEmailAddress(@PathVariable String emailAddress) {
		
		return services.findUserNameByEmailAddress(emailAddress);
	}

}