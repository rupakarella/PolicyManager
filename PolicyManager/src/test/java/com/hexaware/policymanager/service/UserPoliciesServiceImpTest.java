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
		userPolicyDTO.setUserPolicyId(101);
		userPolicyDTO.setStartDate(LocalDate.now());
        userPolicyDTO.setEndDate(LocalDate.now().plusYears(1)); 
        userPolicyDTO.setDurationInYears(1);
        service.createUserPolicy(userPolicyDTO);
        assertNotNull(userPolicyDTO);
	}

	@Test
	void testUpdateUserPolicy() {
		UserPoliciesDTO userPolicyDTO= service.getbyUserPolicyId(101);
		
		userPolicyDTO.setDurationInYears(3);
		userPolicyDTO.setStartDate(LocalDate.now());
		userPolicyDTO.setEndDate(LocalDate.now().plusYears(3));
		
		service.updateUserPolicy(userPolicyDTO);
		UserPoliciesDTO updateduserPolicy = service.getbyUserPolicyId(101);
		//assertEquals(3,updateduserPolicy.getDurationInYears());
		assertEquals(101, updateduserPolicy.getUserPolicyId());
		 System.out.println("Original User Policy: " + userPolicyDTO);
		 System.out.println("Updated User Policy: " + updateduserPolicy);
	}

	@Test
	void testDeleteUserPolicyByPolicyId() {
		String result=service.deleteUserPolicyByPolicyId(1);
		assertEquals(" record deleted ", result);
        
        //assertNull(service.getbyUserPolicyId(1));
	}

	@Test
	void testGetUserPolicyByPolicyId() {
		
	}

	@Test
	void testGetUserPolicyByUserId() {
		List<UserPolicies> userPolicies = service.getUserPolicyByUserId(1);
        assertNotNull(userPolicies);
	}

	@Test
	void testGetAllUserPolicies() {
		List<UserPolicies> list = service.getAllUserPolicies();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

}
