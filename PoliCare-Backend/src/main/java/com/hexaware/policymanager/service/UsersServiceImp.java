package com.hexaware.policymanager.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.DuplicateUserException;
import com.hexaware.policymanager.exception.UserNotFoundException;
import com.hexaware.policymanager.repository.AddressRepository;
import com.hexaware.policymanager.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class UsersServiceImp implements IUsersService {

	Logger logger = LoggerFactory.getLogger(UsersServiceImp.class);

	@Autowired
	UsersRepository usersRepo;

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Users registerUser(UsersDTO userDTO) throws DuplicateUserException {
		try {
			Users existingUser = usersRepo.getUserByEmailAddress(userDTO.getEmailAddress());
			if (existingUser != null) {
				throw new DuplicateUserException(
						"User with email address " + userDTO.getEmailAddress() + " already exists");
			}

			Users user = new Users();
			user.setUserId(userDTO.getUserId());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setEmailAddress(userDTO.getEmailAddress());
			user.setContactNumber(userDTO.getContactNumber());
			user.setUserType(userDTO.getUserType());
			user.setDateOfBirth(userDTO.getDateOfBirth());
			user.setAddress(userDTO.getAddress());
			user.setPanNumber(userDTO.getPanNumber());
			user.setEmployerName(userDTO.getEmployerName());
			user.setSalary(userDTO.getSalary());
			user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

			logger.info("User registered successfully");
			return usersRepo.save(user);
		} catch (DuplicateUserException e) {
			logger.error("Error registering user: {}", e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error("Error registering user", e);
			throw new RuntimeException("Error registering user", e);
		}
	}

	@Override
	public Users updateUser(UsersDTO userDTO) {
		try {
			Optional<Users> optionalUser = usersRepo.findById(userDTO.getUserId());
			if (optionalUser.isPresent()) {
				Users user = optionalUser.get();
				user.setFirstName(userDTO.getFirstName());
				user.setLastName(userDTO.getLastName());
				user.setEmailAddress(userDTO.getEmailAddress());
				user.setContactNumber(userDTO.getContactNumber());
				user.setUserType(userDTO.getUserType());
				user.setDateOfBirth(userDTO.getDateOfBirth());
				user.setAddress(userDTO.getAddress());
				user.setPanNumber(userDTO.getPanNumber());
				user.setEmployerName(userDTO.getEmployerName());
				user.setSalary(userDTO.getSalary());
				user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
				user.setUserPolicies(userDTO.getUserPolicies());

				Users updatedUser = usersRepo.save(user);
				logger.info("User updated successfully: {}", updatedUser);
				return updatedUser;
			} else {
				throw new UserNotFoundException("User not found with ID: " + userDTO.getUserId());
			}
		} catch (Exception e) {
			logger.error("User is not updated", e);
			throw new RuntimeException("Error updating user", e);
		}
	}

	@Override
	public String deleteByUserId(long userId) {
		try {
			if (!usersRepo.existsById(userId)) {
				throw new UserNotFoundException("User not found with ID: " + userId);
			}
			usersRepo.deleteById(userId);

			logger.info("User deleted successfully with ID: {}", userId);
			return "User deleted successfully with ID: " + userId;
		} catch (Exception e) {
			logger.error("Error deleting user", e);
			throw new RuntimeException("Error deleting user", e);
		}
	}

	@Override
	public UsersDTO getById(long userId) {
		try {
			Optional<Users> optional = usersRepo.findById(userId);
			if (optional.isPresent()) {
				Users users = optional.get();
				UsersDTO userDTO = new UsersDTO();
				userDTO.setUserId(users.getUserId());
				userDTO.setEmailAddress(users.getEmailAddress());
				userDTO.setContactNumber(users.getContactNumber());
				userDTO.setPassword(users.getPassword());
				userDTO.setFirstName(users.getFirstName());
				userDTO.setLastName(users.getLastName());
				userDTO.setDateOfBirth(users.getDateOfBirth());
				userDTO.setPanNumber(users.getPanNumber());
				userDTO.setEmployerType(users.getEmployerType());
				userDTO.setEmployerName(users.getEmployerName());
				userDTO.setSalary(users.getSalary());
				userDTO.setUserType(users.getUserType());
				userDTO.setAddress(users.getAddress());

				logger.info("User retrieved successfully by ID: {}", userId);

				return userDTO;
			} else {
				throw new UserNotFoundException("User not found with ID: " + userId);
			}
		} catch (Exception e) {
			logger.error("User Cannot be retrived", e);
			throw new RuntimeException("Error getting user by ID", e);
		}
	}

	@Override
	public Users getUserByEmail(String emailAddress) {
		try {
			Users user = usersRepo.getUserByEmailAddress(emailAddress);
			if (user == null) {
				throw new UserNotFoundException("User not found with email: " + emailAddress);
			}
			logger.info("User retrieved successfully by email: {}", emailAddress);
			return user;

		} catch (Exception e) {
			logger.error("User cannot be retrived with email address", e);
			throw new RuntimeException("Error getting user by email", e);
		}
	}

	@Override
	public List<Users> getUserByUserType(String userType) {
		try {
			List<Users> users = usersRepo.getUserByUserType(userType);
			if (users.isEmpty()) {
				throw new UserNotFoundException("No users found with user type: " + userType);
			}
			return users;
		} catch (Exception e) {
			logger.error("Error getting users by user type", e);
			throw new RuntimeException("Error getting users by user type", e);
		}
	}

	@Override
	public Users getUserBycontactNumber(String contactNumber) {
		try {
			Users user = usersRepo.getUserByContactNumber(contactNumber);
			if (user == null) {
				throw new UserNotFoundException("User not found with contact number: " + contactNumber);
			}

			logger.info("User retrieved successfully by contact number: {}", contactNumber);

			return user;
		} catch (Exception e) {
			logger.error("Error getting user by contact number", e);
			throw new RuntimeException("Error getting user by contact number", e);
		}
	}

	@Override
	public List<Users> getAllUsers() {
		List<Users> users = usersRepo.findAll();
		logger.info("Retrieved all users successfully");

		return users;
	}

	@Override
	public String findUserTypeByEmailAddress(String emailAddress) {

		return usersRepo.findUserTypeByEmailAddress(emailAddress);
	}

	@Override
	public long findUserIdByEmailAddress(String emailAddress) {

		return usersRepo.findUserIdByEmailAddress(emailAddress);
	}

	@Override
	public String findUserNameByEmailAddress(String emailAddress) {

		return usersRepo.findUserNameByEmailAddress(emailAddress);
	}

	@Override
	public String findEmployerTypeByEmailAddress(String emailAddress) {
		return usersRepo.findEmployerTypeByEmailAddress(emailAddress);
	}
}