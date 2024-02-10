package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.UserPoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.UserPolicies;
import com.hexaware.policymanager.entities.Users;
import com.hexaware.policymanager.exception.UserNotFoundException;
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
		userPolicyDTO.setUserId(1); 
        userPolicyDTO.setPolicyId(1); 
		//userPolicyDTO.setStartDate(Date.valueOf("2031-12-11"));
		Date startDateUtil = Date.valueOf("2031-12-11");
	    // Convert java.util.Date to java.sql.Date
	    java.sql.Date startDateSql = new java.sql.Date(startDateUtil.getTime());
	    // Set the start date in the userPolicyDTO
	    userPolicyDTO.setStartDate(startDateSql);
        userPolicyDTO.setDurationInYears(5);
        
        UserPolicies createdUserPolicy = service.createUserPolicy(userPolicyDTO);
        assertNotNull(createdUserPolicy);
        assertEquals(1, createdUserPolicy.getUser().getUserId());
        assertEquals(1, createdUserPolicy.getPolicy().getPolicyId());
	}

	@Test
	void testUpdateUserPolicy() {
		UserPoliciesDTO userPolicyDTO = service.getbyUserPolicyId(52);
		Date startDateUtil = Date.valueOf("2031-12-11");
	    java.sql.Date startDateSql = new java.sql.Date(startDateUtil.getTime());
	    userPolicyDTO.setStartDate(startDateSql);
        userPolicyDTO.setDurationInYears(2);
  

        UserPolicies updatedUserPolicy = service.updateUserPolicy(userPolicyDTO);
        assertNotNull(updatedUserPolicy);
        assertEquals(52, updatedUserPolicy.getUserPolicyId());
        assertEquals(2, updatedUserPolicy.getDurationInYears());
	}

	@Test
	void testDeleteUserPolicyByPolicyId() {
		String result=service.deleteUserPolicyById(302);
		assertEquals("Record deleted", result);
        
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
	    UserPoliciesDTO foundUserPolicy = service.getbyUserPolicyId(252);
	    assertNotNull(foundUserPolicy);
	    assertEquals(252, foundUserPolicy.getUserPolicyId());
	}
	
	
	@Test
	void testGetUserPoliciesByUserId() {
		List<UserPolicies> userpolicies = service.getUserPoliciesByUserId(1);
		assertNotNull(userpolicies);
		for (UserPolicies policy : userpolicies) {
			Users retrivedUser = policy.getUser();		
			assertEquals(1,retrivedUser.getUserId() );
		}
	}
}