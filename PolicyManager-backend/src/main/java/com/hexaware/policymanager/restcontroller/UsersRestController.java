
package com.hexaware.policymanager.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

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

import com.hexaware.policymanager.dto.Password;

import com.hexaware.policymanager.dto.UsersDTO;

import com.hexaware.policymanager.entities.Users;

import com.hexaware.policymanager.exception.DuplicateUserException;

import com.hexaware.policymanager.exception.UserNotFoundException;

import com.hexaware.policymanager.service.IUsersService;

@CrossOrigin(origins = "http://localhost:58118")

@RestController

@RequestMapping(value = "/api/v1/users")

public class UsersRestController {

	@Autowired

	IUsersService usersService;

	@PostMapping(value = "/register")

	public ResponseEntity<Users> registerUser(@RequestBody UsersDTO userDTO)
			throws RuntimeException, DuplicateUserException {

		Users registeredUser = usersService.registerUser(userDTO);

		return ResponseEntity.ok(registeredUser);

	}

	@PutMapping(value = "/update")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<Users> updateUser(@RequestBody UsersDTO userDTO) throws RuntimeException {
		Users updatedUser = usersService.updateUser(userDTO);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping(value = "/delete/{userId}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<String> deleteByUserId(@PathVariable long userId) throws UserNotFoundException {
		String result = usersService.deleteByUserId(userId);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/get-userId/{userId}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<UsersDTO> getUserById(@PathVariable long userId) throws UserNotFoundException {
		UsersDTO userDTO = usersService.getById(userId);
		return ResponseEntity.ok(userDTO);
	}

	@GetMapping(value = "/get-email/{email}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<Users> getUserPolicyByEmail(@PathVariable String email) throws UserNotFoundException {
		Users user = usersService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/get-type/{userType}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<Users>> getUserByUserType(@PathVariable String userType) throws UserNotFoundException {
		List<Users> users = usersService.getUserByUserType(userType);
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/get-contactnumber/{contactNumber}")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<Users> getUserByContactNumber(@PathVariable String contactNumber)
			throws UserNotFoundException {
		Users user = usersService.getUserBycontactNumber(contactNumber);
		return ResponseEntity.ok(user);
	}

	@GetMapping(value = "/get-all")
	@PreAuthorize("hasAuthority('Admin')")
	public ResponseEntity<List<Users>> getAllUsers() {
		List<Users> users = usersService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping(value = "/get-userType-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<String> findUserTypeByEmailAddress(@PathVariable String emailAddress) {
		String userType = usersService.findUserTypeByEmailAddress(emailAddress);
		return ResponseEntity.ok(userType);
	}

	@GetMapping(value = "/get-userId-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<Long> findUserIdByEmailAddress(@PathVariable String emailAddress) {
		long userId = usersService.findUserIdByEmailAddress(emailAddress);
		return ResponseEntity.ok(userId);
	}

	@GetMapping(value = "/get-userName-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<String> findUserNameByEmailAddress(@PathVariable String emailAddress) {
		String userName = usersService.findUserNameByEmailAddress(emailAddress);
		return ResponseEntity.ok(userName);
	}

	@GetMapping(value = "/get-employerType-by-emailAddress/{emailAddress}")
	@PreAuthorize("hasAnyAuthority('Admin','User')")
	public ResponseEntity<String> findEmployerTypeByEmailAddress(@PathVariable String emailAddress) {
		String employerType = usersService.findEmployerTypeByEmailAddress(emailAddress);
		return ResponseEntity.ok(employerType);
	}

	@PutMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody Password password) {
		String result = usersService.updateUserPassword(password);
		return ResponseEntity.ok(result);
	}

}