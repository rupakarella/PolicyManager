package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/api/v1/users")
public class UsersRestController {

	@Autowired
	IUsersService UserService;

	@PostMapping(value = "/register")
	public Users registerUser(@RequestBody UsersDTO userDTO)throws RuntimeException, DuplicateUserException  {
		
		return UserService.registerUser(userDTO);
	}

	@PutMapping(value = "/update")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public Users updateUser(@RequestBody UsersDTO userDTO)throws RuntimeException  {
		return UserService.updateUser(userDTO);
	}

	@DeleteMapping(value = "/delete/{userId}")
	@PreAuthorize("hasAuthority('Admin')")
	public String deleteByUserId(@PathVariable long userId) throws UserNotFoundException {
		return UserService.deleteByUserId(userId);
	}

	@GetMapping(value = "/get-userId/{userId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public UsersDTO getUserById(@PathVariable long userId) throws UserNotFoundException {
		return UserService.getById(userId);
	}
	
	@GetMapping(value="/get-email/{email}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public Users getUserPolicyByEmail(@PathVariable String email) throws UserNotFoundException
	{
		return UserService.getUserByEmail(email);
	}
	
	@GetMapping(value="/get-type/{userType}")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Users> getUserByUserType(@PathVariable String userType)throws UserNotFoundException 
	{
		return UserService.getUserByUserType(userType);
	}
	
	@GetMapping(value="/get-contactnumber/{contactNumber}")
	@PreAuthorize("hasAuthority('Admin')")
	public Users getUserByConatctno(@PathVariable String contactNumber) throws UserNotFoundException
	{
		return UserService.getUserBycontactNumber(contactNumber);
	}
	
	@GetMapping(value="/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public List<Users> getAllUsers()
	{
		return UserService.getAllUsers();
	}
	@GetMapping(value="/get-userType-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public String findUserTypeByEmailAddress(@PathVariable String emailAddress) {
		
		return UserService.findUserTypeByEmailAddress(emailAddress);
	}
	
	@GetMapping(value="/get-userId-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public long findUserIdByEmailAddress(@PathVariable String emailAddress) {
		
		return UserService.findUserIdByEmailAddress(emailAddress);
	}
	
	@GetMapping(value="/get-userName-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public String findUserNameByEmailAddress(@PathVariable String emailAddress) {
		
		return UserService.findUserNameByEmailAddress(emailAddress);
	}

}