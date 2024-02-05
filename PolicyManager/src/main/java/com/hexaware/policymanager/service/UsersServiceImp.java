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
	public UsersDTO getById(long userId) {
		Optional<Users> optional= usersRepo.findById(userId);
		Users users = null;
		UsersDTO userDTO=new UsersDTO();
			if (optional.isPresent()) {
				users = optional.get();
		        if (users != null) {
		        	userDTO.setEmailAddress(users.getEmailAddress());
		        	userDTO.setContactNo(users.getContactNo());
		        	userDTO.setPassword(users.getPassword());
		        	userDTO.setFirstName(users.getFirstName());
		        	userDTO.setLastName(users.getLastName());
		        	userDTO.setDateOfBirth(users.getDateOfBirth());
		        	userDTO.setPanNo(users.getPanNo());
		        	userDTO.setEmployerType(users.getEmployerType());
		        	userDTO.setEmployerName(users.getEmployerName());
		        	userDTO.setSalary(users.getSalary());
		        	userDTO.setUserType(users.getUserType());
	
		        }
		    }
		    return userDTO;
	}

	@Override
	public Users getUserByEmail(String emailAddress) {
		
		return usersRepo.getUserByemailAddress(emailAddress);
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
	public List<Users> getAllUsers() {
		return usersRepo.findAll();
	}

}
