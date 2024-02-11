package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.DuplicateUserException;
import com.hexaware.policymanager.exception.UserNotFoundException;

public interface IUsersService {
	public Users registerUser(UsersDTO userDTO) throws DuplicateUserException;

	public Users updateUser(UsersDTO userDTO) throws UserNotFoundException;

	public String deleteByUserId(long userId) throws UserNotFoundException;

	public UsersDTO getById(long userId) throws UserNotFoundException;

	public Users getUserByEmail(String email) throws UserNotFoundException;

	public List<Users> getUserByUserType(String userType) throws UserNotFoundException;

	public Users getUserBycontactNumber(String contactNumber) throws UserNotFoundException;

	public List<Users> getAllUsers();

	public String findUserTypeByEmailAddress(String emailAddress);

	public long findUserIdByEmailAddress(String emailAddress);

	public String findUserNameByEmailAddress(String emailAddress);
}