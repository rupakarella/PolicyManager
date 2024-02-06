package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.UserNotFoundException;

public interface IUsersService {
	public Users registerUser(UsersDTO userDTO);
	public Users updateUser(UsersDTO userDTO);
	public String deleteByUserId(long userId);
	public UsersDTO getById(long userId);
    public Users getUserByEmail(String email);
    public List<Users> getUserByUserType(String userType) throws UserNotFoundException;
    public Users getUserBycontactNo(String contactNo);
	public List<Users> getAllUsers();
}
