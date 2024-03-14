package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.DuplicateUserException;

@SpringBootTest
class UsersServiceImpTest {

	@Autowired
	IUsersService usersService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRegisterUser() throws DuplicateUserException {
		UsersDTO userDTO = new UsersDTO();
		userDTO.setEmailAddress("madhavi7@gmail.com");
		userDTO.setContactNumber("9703315171");
		userDTO.setPassword("madhavi@123");
		userDTO.setFirstName("Madhavi");
		userDTO.setLastName("Patlolla");
		userDTO.setDateOfBirth(LocalDate.of(2001, 12, 11));
		userDTO.setPanNumber("SGKPP9871G");
		userDTO.setEmployerName("Madhavi Patlolla");
		userDTO.setSalary(42000);
		userDTO.setUserType("Admin");
		usersService.registerUser(userDTO);
		assertNotNull(userDTO);

	}

	@Test
	void testUpdateUser() {
		UsersDTO userDTO = usersService.getById(10027);
		userDTO.setEmailAddress("madhavi@gmail.com");
		userDTO.setContactNumber("9703315171");
		userDTO.setPassword("madhavi@123");
		userDTO.setFirstName("Madhavi");
		userDTO.setLastName("Patlolla");
		userDTO.setDateOfBirth(LocalDate.of(2001, 12, 11));
		userDTO.setPanNumber("SGKPP9871G");
		userDTO.setEmployerName("Raju Patlolla");
		userDTO.setSalary(42000);
		userDTO.setUserType("Admin");
		Users UpdatedUsers = usersService.updateUser(userDTO);
		assertEquals("Madhavi", UpdatedUsers.getFirstName());

	}

	@Test
	void testDeleteByUserId() {
		String result = usersService.deleteByUserId(10000);
		assertEquals("User deleted successfully with ID: 10000", result);
	}

	@Test
	void testGetById() {
		UsersDTO userDTO = usersService.getById(10000);
		assertNotNull(userDTO);
		assertEquals(10000, userDTO.getUserId());
	}

	@Test
	void testGetUserByEmail() {
		Optional<Users> userOptional = Optional.ofNullable(usersService.getUserByEmail("lakshmiprasannaramidi2001@gmail.com"));
		assertTrue(userOptional.isPresent());
		Users user = userOptional.get();
	}

	@Test
	void testGetUserByUserType() {
		List<Users> userList = usersService.getUserByUserType("Admin");
		for (Users user : userList) {
			assertEquals("Admin", user.getUserType());
		}

	}

	@Test
	void testGetUserBycontactNo() {
		Users userDTO = usersService.getUserBycontactNumber("7671946546");
		assertEquals("7671946546", userDTO.getContactNumber());

	}

	@Test
	void testGetAllUsers() {
		List<Users> userDTO = usersService.getAllUsers();
		boolean flag = userDTO.isEmpty();
		assertFalse(flag);
	}

	@Test
	void testFindUserTypeByEmailAddress() {
		String result = usersService.findUserTypeByEmailAddress("anagha@gmail.com");
		assertEquals("User", result);
	}

	@Test
	void testFindUserIdByEmailAddress() {
		long result = usersService.findUserIdByEmailAddress("rupakarella212@gmail.com");
		assertEquals(10000, result);
	}

	@Test
	void testFindUserNameByEmailAddress() {
		String result = usersService.findUserNameByEmailAddress("rupakarella212@gmail.com");
		assertEquals("Rupa", result);
	}

}