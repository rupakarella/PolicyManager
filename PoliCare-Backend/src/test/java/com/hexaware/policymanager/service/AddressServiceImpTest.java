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
        addressDTO.setCityPincode(534211);

        Users user = new Users();
        user.setEmailAddress("madhu1@gmail.com");
        user.setPassword("madhavi@123");
        user.setFirstName("Madhavi");
        user.setLastName("Patlolla");
        user.setDateOfBirth(LocalDate.of(2001, 12, 11));
        user.setPanNumber("SGKPP9871G");
        user.setEmployerName("Madhavi Patlolla");
        user.setSalary(42000);
        user.setContactNumber("7452189632");
        user.setUserType("Admin");
        addressDTO.setUsers(user);
		addressService.createAddress(addressDTO);
		assertNotNull(addressDTO);
	}

	@Test
	void testUpdateAddress() {
		AddressDTO addressDTO = addressService.getByAddressId(70002);
		addressDTO.setAddressLine("rainbow towers");
		addressDTO.setCity("Vijayawada");
		addressDTO.setCityPincode(512512);
		addressDTO.setState("Andhra Pradesh");
		addressService.updateAddress(addressDTO);
		AddressDTO updatedAddress = addressService.getByAddressId(70002);
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