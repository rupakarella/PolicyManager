//package com.hexaware.policymanager.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.hexaware.policymanager.dto.UsersDTO;
//import com.hexaware.policymanager.entities.Users;
//import com.hexaware.policymanager.exception.DuplicateUserException;
//
//@SpringBootTest
//class UsersServiceImpTest {
//
//	@Autowired
//	IUsersService usersService;
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@Test
//	void testRegisterUser() throws DuplicateUserException {
//		UsersDTO userDTO = new UsersDTO();
//		userDTO.setUserId(2000);
//		userDTO.setEmailAddress("madhavi7@gmail.com");
//		userDTO.setContactNumber("9703315171");
//		userDTO.setPassword("madhavi@123");
//		userDTO.setFirstName("Madhavi");
//		userDTO.setLastName("Patlolla");
//		userDTO.setDateOfBirth(LocalDate.of(2001, 12, 11));
//		userDTO.setPanNumber("SGKPP9871G");
//		userDTO.setEmployerType("Permanent");
//		userDTO.setEmployerName("Madhavi Patlolla");
//		userDTO.setSalary(42000);
//		userDTO.setUserType("Admin");
//		usersService.registerUser(userDTO);
//		assertNotNull(userDTO);
//
//	}
//
//	@Test
//	void testUpdateUser() {
//		UsersDTO userDTO = usersService.getById(852);
//		userDTO.setEmailAddress("madhavi@gmail.com");
//		userDTO.setContactNumber("9703315171");
//		userDTO.setPassword("madhavi@123");
//		userDTO.setFirstName("Madhavi");
//		userDTO.setLastName("Patlolla");
//		userDTO.setDateOfBirth(LocalDate.of(2001, 12, 11));
//		userDTO.setPanNumber("SGKPP9871G");
//		userDTO.setEmployerType("Permanent");
//		userDTO.setEmployerName("Madhavi Patlolla");
//		userDTO.setSalary(42000);
//		userDTO.setUserType("Admin");
//		Users UpdatedUsers = usersService.updateUser(userDTO);
//		assertEquals("Madhavi", UpdatedUsers.getFirstName());
//
//	}
//
//	@Test
//	void testDeleteByUserId() {
//		String result = usersService.deleteByUserId(153);
//		assertEquals("User deleted successfully with ID: 153", result);
//	}
//
//	@Test
//	void testGetById() {
//		UsersDTO userDTO = usersService.getById(852);
//		assertNotNull(userDTO);
//		assertEquals(852, userDTO.getUserId());
//	}
//
//	@Test
//	void testGetUserByEmail() {
//		Optional<Users> userOptional = Optional.ofNullable(usersService.getUserByEmail("rupakarella212@gmail.com"));
//		assertTrue(userOptional.isPresent());
//		Users user = userOptional.get();
//	}
//
//	@Test
//	void testGetUserByUserType() {
//		List<Users> userList = usersService.getUserByUserType("Admin");
//		for (Users user : userList) {
//			assertEquals("Admin", user.getUserType());
//		}
//
//	}
//
//	@Test
//	void testGetUserBycontactNo() {
//		Users userDTO = usersService.getUserBycontactNumber("8919994219");
//		assertEquals("8919994219", userDTO.getContactNumber());
//
//	}
//
//	@Test
//	void testGetAllUsers() {
//		List<Users> userDTO = usersService.getAllUsers();
//		boolean flag = userDTO.isEmpty();
//		assertFalse(flag);
//	}
//
//	@Test
//	void testFindUserTypeByEmailAddress() {
//		String result = usersService.findUserTypeByEmailAddress("madhavi7@gmail.com");
//		assertEquals("Admin", result);
//	}
//
//	@Test
//	void testFindUserIdByEmailAddress() {
//		long result = usersService.findUserIdByEmailAddress("madhavi7@gmail.com");
//		assertEquals(1052, result);
//	}
//
//	@Test
//	void testFindUserNameByEmailAddress() {
//		String result = usersService.findUserNameByEmailAddress("madhavi7@gmail.com");
//		assertEquals("Madhavi", result);
//	}
//
//}