package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.AddressDTO;
import com.hexaware.policymanager.entities.Address;
@SpringBootTest
class AddressServiceImpTest {
	@Autowired
	IAddressService service;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCreateAddress() {
		AddressDTO addressDTO = new AddressDTO();
		
        addressDTO.setAddressId(109);
        addressDTO.setAddressLine("ganesh Towers");
        addressDTO.setCity("Hyderabad");
        addressDTO.setState("Telangana");
        addressDTO.setCityPincode(26); 
        service.createAddress(addressDTO);
        assertNotNull(addressDTO);
	}

	@Test
	void testUpdateAddress() {
		AddressDTO addressDTO = service.getByAddressId(3);
		addressDTO.setAddressLine("rainbow towers");
		addressDTO.setCity("Vijayawada");
		addressDTO.setCityPincode(12);
		addressDTO.setState("Andhra Pradesh");
		service.updateAddress(addressDTO);
		AddressDTO updatedAddress = service.getByAddressId(3);
		assertEquals("Vijayawada",updatedAddress.getCity());
		
	}

	@Test
	void testDeleteByAddressId() {
	
		 String result=service.deleteByAddressId(202);
		 AddressDTO deletedAddressDTO = service.getByAddressId(202);
		 assertEquals("record deleted", result);     
	}

	@Test
	void testGetbyAddressId() {
		AddressDTO addressDTO = service.getByAddressId(3);
        assertNotNull(addressDTO);
        assertEquals(3, addressDTO.getAddressId());
	}

	@Test
	void testGetByState() {
		List<Address> address=service.getByState("Andhra Pradesh");
		assertNotNull(address);
		for (Address address1 : address) {
		    String state = address1.getState();
		    assertEquals("Andhra Pradesh",state);
		}
		
	}

	@Test
	void testGetByCity() {
		List<Address> address=service.getByCity("bhimavaram");
		assertNotNull(address);
		for (Address address1 : address) {
		    String city = address1.getCity();
		    assertEquals("bhimavaram",city);
		}
	}

	@Test
	void testGetAllAddress() {
		List<Address> list = service.getAllAddress();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

}
