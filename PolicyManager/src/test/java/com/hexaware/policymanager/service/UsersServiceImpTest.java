//package com.hexaware.policymanager.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.sql.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.hexaware.policymanager.dto.UsersDTO;
//import com.hexaware.policymanager.entities.Users;
//
//@SpringBootTest
//class UsersServiceImpTest {
//
//	@Autowired
//	IUsersService service;
//
//	@Test
//	void testRegisterUser() {
//		UsersDTO userDTO = new UsersDTO();
//		userDTO.setUserId(2000);
//		userDTO.setEmailAddress("madhavi@gmail.com");
//<<<<<<< HEAD
//		userDTO.setContactNo("91970331517");
//=======
//		userDTO.setContactNumber("9703315171");
//>>>>>>> f7b90dada1af4b6fb22e2b85ba3266df2fc09529
//		userDTO.setPassword("madhavi@123");
//		userDTO.setFirstName("Madhavi");
//		userDTO.setLastName("Patlolla");
//		userDTO.setDateOfBirth(Date.valueOf("2001-06-15"));
//<<<<<<< HEAD
//		userDTO.setPanNo("SGKPP09871");
//=======
//		userDTO.setPanNumber("SGKPP9871G");
//>>>>>>> f7b90dada1af4b6fb22e2b85ba3266df2fc09529
//		userDTO.setEmployerType("Permanent");
//		userDTO.setEmployerName("Madhavi Patlolla");
//
//		service.registerUser(userDTO);
//
//		assertNotNull(userDTO);
//
//	}
//
//	@Test
//	void testUpdateUser() {
//		UsersDTO userDTO=service.getById(100);
//		userDTO.setEmailAddress("prasanna@gmail.com");
//<<<<<<< HEAD
//		userDTO.setContactNo("9876543210");
//=======
//		userDTO.setContactNumber("9876543210");
//>>>>>>> f7b90dada1af4b6fb22e2b85ba3266df2fc09529
//		userDTO.setPassword("prasanna@28%");
//		userDTO.setFirstName("Prasanna");
//		userDTO.setLastName("Ramidi");
//		userDTO.setEmployerName("Prasanna Ramidi");
//		
//		Users UpdatedUsers=service.updateUser(userDTO);
//		assertEquals("Prasanna", UpdatedUsers.getFirstName());
//		
//		
//	}
//
//	@Test
//	void testDeleteByUserId() {
//		String result = service.deleteByUserId(105);
//		String deleteuserDTO = service.deleteByUserId(105);
//		assertEquals("record deleted", result);
//	}
//
//	@Test
//	void testGetById() {
//		UsersDTO userDTO = service.getById(102);
//		
//		assertNotNull(userDTO);
//		assertEquals(102, userDTO.getUserId());
//
//	}
//
//	@Test
//	void testGetUserByEmail() {
//		Optional<Users> userOptional = Optional
//				.ofNullable(service.getUserByEmail("lakshmiprasannaramidi2001@gmail.com"));
//		assertTrue(userOptional.isPresent());
//		Users user = userOptional.get();
//	}
//
//	@Test
//	void testGetUserByUserType() {
//		List<Users> userList = service.getUserByUserType("User");
//		for (Users user : userList) {
//			assertEquals("User", user.getUserType());
//		}
//
//	}
//
//	@Test
//	void testGetUserBycontactNo() {
//<<<<<<< HEAD
//		Users userDTO = service.getUserBycontactNo("919876543210");
//		assertEquals("919876543210", userDTO.getContactNo());
//=======
//		Users userDTO = service.getUserBycontactNumber("919876543210");
//		assertEquals("919876543210", userDTO.getContactNumber());
//>>>>>>> f7b90dada1af4b6fb22e2b85ba3266df2fc09529
//
//	}
//
//	@Test
//	void testGetAllUsers() {
//		List<Users> userDTO = service.getAllUsers();
//		boolean flag = userDTO.isEmpty();
//		assertFalse(flag);
//	}
//<<<<<<< HEAD
//}
//=======
//}
//>>>>>>> f7b90dada1af4b6fb22e2b85ba3266df2fc09529
