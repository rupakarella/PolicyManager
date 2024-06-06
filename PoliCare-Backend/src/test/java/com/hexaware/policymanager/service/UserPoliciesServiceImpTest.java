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
import com.hexaware.policymanager.entities.Users;
@SpringBootTest

class UserPoliciesServiceImpTest {
	
	@Autowired
	IUserPoliciesService userPoliciesService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	
	@Test
    void testCreateUserPolicy() {
        UserPoliciesDTO userPolicyDTO = new UserPoliciesDTO();
        userPolicyDTO.setUserId(10026);
        userPolicyDTO.setPolicyId(100015);
        LocalDate startDate = LocalDate.of(2031, 12, 11);
        userPolicyDTO.setStartDate(startDate);
        userPolicyDTO.setDurationInYears(5);

        UserPolicies createdUserPolicy = userPoliciesService.createUserPolicy(userPolicyDTO);
        assertNotNull(createdUserPolicy);
        assertEquals(10026, createdUserPolicy.getUser().getUserId());
        assertEquals(100015, createdUserPolicy.getPolicy().getPolicyId());
    }
	

	@Test
    void testUpdateUserPolicy() {
        UserPolicies userPolicy = userPoliciesService.getbyUserPolicyId(40017);
        UserPoliciesDTO userPolicyDTO = new UserPoliciesDTO();
        LocalDate startDate = LocalDate.of(2025, 12, 11);
        userPolicyDTO.setPolicyId(100002);
        userPolicyDTO.setUserPolicyId(userPolicy.getUserPolicyId());
        userPolicyDTO.setStartDate(startDate);
        userPolicyDTO.setDurationInYears(2);

        UserPolicies updatedUserPolicy = userPoliciesService.updateUserPolicy(userPolicyDTO);
        assertNotNull(updatedUserPolicy);
        assertEquals(40017, updatedUserPolicy.getUserPolicyId());
        assertEquals(2, updatedUserPolicy.getDurationInYears());
    }

	@Test
	void testDeleteUserPolicyByPolicyId() {
		String result=userPoliciesService.deleteUserPolicyById(40021);
		assertEquals("Record deleted", result);
        
        //assertNull(service.getbyUserPolicyId(1));
	}


	@Test
	void testGetAllUserPolicies() {
		List<UserPolicies> list = userPoliciesService.getAllUserPolicies();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}
	@Test
	void testGetByUserPolicyId() {
	    UserPolicies foundUserPolicy = userPoliciesService.getbyUserPolicyId(40014);
	    assertNotNull(foundUserPolicy);
	    assertEquals(40014, foundUserPolicy.getUserPolicyId());
	}
	
	
	@Test
	void testGetUserPoliciesByUserId() {
		List<UserPolicies> userpolicies = userPoliciesService.getUserPoliciesByUserId(10017);
		assertNotNull(userpolicies);
		for (UserPolicies policy : userpolicies) {
			Users retrivedUser = policy.getUser();		
			assertEquals(10017,retrivedUser.getUserId() );
		}
	}
}