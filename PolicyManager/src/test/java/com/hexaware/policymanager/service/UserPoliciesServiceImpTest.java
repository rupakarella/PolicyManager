package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.UserPolicies;
@SpringBootTest
class UserPoliciesServiceImpTest {
	@Autowired
	IUserPoliciesService service;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCreateUserPolicy() {
		UserPoliciesDTO userPolicyDTO=new UserPoliciesDTO();
		userPolicyDTO.setUserPolicyId(123);
		userPolicyDTO.setUserId(1); 
        userPolicyDTO.setPolicyId(1); 
		userPolicyDTO.setStartDate(Date.valueOf("2001-12-11"));
        userPolicyDTO.setEndDate(Date.valueOf("2020-12-11")); 
        userPolicyDTO.setDurationInYears(1);
        UserPolicies createdUserPolicy = service.createUserPolicy(userPolicyDTO);
        assertNotNull(createdUserPolicy);
        assertEquals(123, createdUserPolicy.getUserPolicyId());
        assertEquals(1, createdUserPolicy.getUser().getUserId());
        assertEquals(1, createdUserPolicy.getPolicy().getPolicyId());
	}

	@Test
	void testUpdateUserPolicy() {
		UserPoliciesDTO userPolicyDTO = service.getbyUserPolicyId(111);

        userPolicyDTO.setDurationInYears(23);
        userPolicyDTO.setStartDate(Date.valueOf("2001-12-11"));
        userPolicyDTO.setEndDate(Date.valueOf("2023-12-11")); 

        UserPolicies updatedUserPolicy = service.updateUserPolicy(userPolicyDTO);
        assertNotNull(updatedUserPolicy);
        assertEquals(111, updatedUserPolicy.getUserPolicyId());
        assertEquals(3, updatedUserPolicy.getDurationInYears());
	}

	@Test
	void testDeleteUserPolicyByPolicyId() {
		String result=service.deleteUserPolicyById(1);
		assertEquals(" record deleted ", result);
        
        //assertNull(service.getbyUserPolicyId(1));
	}


	@Test
	void testGetAllUserPolicies() {
		List<UserPolicies> list = service.getAllUserPolicies();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}
	@Test
	void testGetByUserPolicyId() {
	    UserPoliciesDTO foundUserPolicy = service.getbyUserPolicyId(101);
	    assertNotNull(foundUserPolicy);
	    assertEquals(101, foundUserPolicy.getUserPolicyId());
	}
}