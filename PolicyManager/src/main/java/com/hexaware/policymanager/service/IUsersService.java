package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;

public interface IUsersService {
	public Users registerUser(UsersDTO userDTO);
	public Users updateUser(UsersDTO userDTO);
	public String deleteByUserId(long userId);
	public Optional<Users> getById(long userId);
    public Users getUserByEmail(String email);
    public List<Users> getUserByUserType(String userType);
    public Users getUserBycontactNo(String contactNo);
	public List<Users> getAllUsers();
}
