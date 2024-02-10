package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
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
	IUsersService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRegisterUser() throws DuplicateUserException {
		UsersDTO userDTO = new UsersDTO();
		userDTO.setUserId(2000);
		userDTO.setEmailAddress("madhavi7@gmail.com");
		userDTO.setContactNumber("9703315171");
		userDTO.setPassword("madhavi@123");
		userDTO.setFirstName("Madhavi");
		userDTO.setLastName("Patlolla");
		userDTO.setDateOfBirth(Date.valueOf("2001-06-15"));
		userDTO.setPanNumber("SGKPP9871G");
		userDTO.setEmployerType("Permanent");
		userDTO.setEmployerName("Madhavi Patlolla");
		userDTO.setSalary(42000);
		userDTO.setUserType("Admin");
		service.registerUser(userDTO);
		assertNotNull(userDTO);

	}

	@Test
	void testUpdateUser() {
		UsersDTO userDTO = service.getById(852);
		userDTO.setEmailAddress("madhavi@gmail.com");
		userDTO.setContactNumber("9703315171");
		userDTO.setPassword("madhavi@123");
		userDTO.setFirstName("Madhavi");
		userDTO.setLastName("Patlolla");
		userDTO.setDateOfBirth(Date.valueOf("2001-06-15"));
		userDTO.setPanNumber("SGKPP9871G");
		userDTO.setEmployerType("Permanent");
		userDTO.setEmployerName("Madhavi Patlolla");
		userDTO.setSalary(42000);
		userDTO.setUserType("Admin");
		Users UpdatedUsers = service.updateUser(userDTO);
		assertEquals("Madhavi", UpdatedUsers.getFirstName());

	}

	@Test
	void testDeleteByUserId() {
		String result = service.deleteByUserId(153);
		assertEquals("User deleted successfully with ID: 153", result);
	}

	@Test
	void testGetById() {
		UsersDTO userDTO = service.getById(852);
		assertNotNull(userDTO);
		assertEquals(852, userDTO.getUserId());
	}

	@Test
	void testGetUserByEmail() {
		Optional<Users> userOptional = Optional.ofNullable(service.getUserByEmail("rupakarella212@gmail.com"));
		assertTrue(userOptional.isPresent());
		Users user = userOptional.get();
	}

	@Test
	void testGetUserByUserType() {
		List<Users> userList = service.getUserByUserType("Admin");
		for (Users user : userList) {
			assertEquals("Admin", user.getUserType());
		}

	}

	@Test
	void testGetUserBycontactNo() {
		Users userDTO = service.getUserBycontactNumber("8919994219");
		assertEquals("8919994219", userDTO.getContactNumber());

	}

	@Test
	void testGetAllUsers() {
		List<Users> userDTO = service.getAllUsers();
		boolean flag = userDTO.isEmpty();
		assertFalse(flag);
	}
	@Test
	void testFindUserTypeByEmailAddress() {
		String result=service.findUserTypeByEmailAddress("madhavi7@gmail.com");
		assertEquals("Admin",result);
	}

	@Test
	void testFindUserIdByEmailAddress() {
		long result=service.findUserIdByEmailAddress("madhavi7@gmail.com");
		assertEquals(1052,result);
	}

	@Test
	void testFindUserNameByEmailAddress() {
		String result=service.findUserNameByEmailAddress("madhavi7@gmail.com");
		assertEquals("Madhavi",result);
	}


}