package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.dto.UsersDTO;
import com.hexaware.policymanager.entities.Address;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.DuplicateUserException;

@SpringBootTest
class AddressServiceImpTest {
	@Autowired
	IAddressService addressService;
	@Autowired
	IUsersService usersService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCreateAddress() throws DuplicateUserException {
		AddressDTO addressDTO = new AddressDTO();

		addressDTO.setAddressLine("ganesh Towers");
		addressDTO.setCity("Hyderabad");
		addressDTO.setState("Telangana");
		addressDTO.setCityPincode(26);
		UsersDTO userDTO = new UsersDTO();
		userDTO.setEmailAddress("madhu2@gmail.com");
		userDTO.setContactNumber("8099787293");
		userDTO.setPassword("madhavi@123");
		userDTO.setFirstName("Madhavi");
		userDTO.setLastName("Patlolla");
		userDTO.setDateOfBirth(LocalDate.of(2001, 12, 11));
		userDTO.setPanNumber("SGKPP9871G");
		userDTO.setEmployerName("Madhavi Patlolla");
		userDTO.setSalary(42000);
		userDTO.setUserType("Admin");
//		Users user = usersService.registerUser(userDTO);
//		addressDTO.setUsers(user);
		addressService.createAddress(addressDTO);
		assertNotNull(addressDTO);
	}

	@Test
	void testUpdateAddress() {
		AddressDTO addressDTO = addressService.getByAddressId(70000);
		addressDTO.setAddressLine("rainbow towers");
		addressDTO.setCity("Vijayawada");
		addressDTO.setCityPincode(12);
		addressDTO.setState("Andhra Pradesh");
		addressService.updateAddress(addressDTO);
		AddressDTO updatedAddress = addressService.getByAddressId(70000);
		assertEquals("Vijayawada", updatedAddress.getCity());

	}

	@Test
	void testDeleteByAddressId() {

		String result = addressService.deleteByAddressId(70003);
		assertEquals("record deleted", result);
	}

	@Test
	void testGetbyAddressId() {
		AddressDTO addressDTO = addressService.getByAddressId(70002);
		assertNotNull(addressDTO);
		assertEquals(70002, addressDTO.getAddressId());
	}

	@Test
	void testGetByState() {
		List<Address> address = addressService.getByState("Andhra Pradesh");
		assertNotNull(address);
		for (Address address1 : address) {
			String state = address1.getState();
			assertEquals("Andhra Pradesh", state);
		}

	}

	@Test
	void testGetByCity() {
		List<Address> address = addressService.getByCity("Tanuku");
		assertNotNull(address);
		for (Address address1 : address) {
			String city = address1.getCity();
			assertEquals("Tanuku", city);
		}
	}

	@Test
	void testGetAllAddress() {
		List<Address> list = addressService.getAllAddress();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

}