package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.repository.AddressRepository;
import com.hexaware.policymanager.repository.UsersRepository;
@Service
public class UsersServiceImp implements IUsersService {
	@Autowired
	UsersRepository usersRepo;
	@Autowired
	AddressRepository addressRepo;
	@Override
	public Users registerUser(UsersDTO userDTO) {
		Users user = new Users();
		user.setUserId(userDTO.getUserId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmailAddress(userDTO.getEmailAddress());
		user.setContactNo(userDTO.getContactNo());
		user.setUserType(userDTO.getUserType());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user.setAddress(userDTO.getAddress());
		user.setPanNo(userDTO.getPanNo());
		user.setEmployerName(userDTO.getEmployerName());
		user.setEmployerType(userDTO.getEmployerType());
		user.setSalary(userDTO.getSalary());
		user.setPassword(userDTO.getPassword());
		
		return usersRepo.save(user);
	}

	@Override
	public Users updateUser(UsersDTO userDTO) {
		Users user = new Users();
		user.setUserId(userDTO.getUserId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmailAddress(userDTO.getEmailAddress());
		user.setContactNo(userDTO.getContactNo());
		user.setUserType(userDTO.getUserType());
		user.setDateOfBirth(userDTO.getDateOfBirth());
		user.setAddress(userDTO.getAddress());
		user.setPanNo(userDTO.getPanNo());
		user.setEmployerName(userDTO.getEmployerName());
		user.setEmployerType(userDTO.getEmployerType());
		user.setSalary(userDTO.getSalary());
		user.setPassword(userDTO.getPassword());
		
		return usersRepo.save(user);
	}

	@Override
	public String deleteByUserId(long userId) {
		usersRepo.deleteById(userId);
		return "record deleted";
		
	}

	@Override
	public Optional<Users> getById(long userId) {
		
		return usersRepo.findById(userId);
	}

	@Override
	public Users getUserByEmail(String email) {
		
		return usersRepo.getUserByEmailAddress(email);
	}

	@Override
	public List<Users> getUserByUserType(String userType) {
		
		return usersRepo.getUserByUserType(userType);
	}

	@Override
	public Users getUserBycontactNo(String contactNo) {
		return usersRepo.getUserBycontactNo(contactNo);
	}

	@Override
	public List<Users> getAllUser() {
		return usersRepo.findAll();
	}

}
