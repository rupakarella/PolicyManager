//package com.hexaware.policymanager.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.hexaware.policymanager.dto.UserPoliciesDTO;
//import com.hexaware.policymanager.entities.UserPolicies;
//import com.hexaware.policymanager.entities.Users;
//@SpringBootTest
//
//class UserPoliciesServiceImpTest {
//	
//	@Autowired
//	IUserPoliciesService userPoliciesService;
//	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	
//	@Test
//    void testCreateUserPolicy() {
//        UserPoliciesDTO userPolicyDTO = new UserPoliciesDTO();
//        userPolicyDTO.setUserId(1);
//        userPolicyDTO.setPolicyId(1);
//        LocalDate startDate = LocalDate.of(2031, 12, 11);
//        userPolicyDTO.setStartDate(startDate);
//        userPolicyDTO.setDurationInYears(5);
//
//        UserPolicies createdUserPolicy = userPoliciesService.createUserPolicy(userPolicyDTO);
//        assertNotNull(createdUserPolicy);
//        assertEquals(1, createdUserPolicy.getUser().getUserId());
//        assertEquals(1, createdUserPolicy.getPolicy().getPolicyId());
//    }
//	
//
//	@Test
//    void testUpdateUserPolicy() {
//        UserPolicies userPolicy = userPoliciesService.getbyUserPolicyId(954);
//        UserPoliciesDTO userPolicyDTO = new UserPoliciesDTO();
//        LocalDate startDate = LocalDate.of(2031, 12, 11);
//        userPolicyDTO.setUserPolicyId(userPolicy.getUserPolicyId());
//        userPolicyDTO.setStartDate(startDate);
//        userPolicyDTO.setDurationInYears(2);
//
//        UserPolicies updatedUserPolicy = userPoliciesService.updateUserPolicy(userPolicyDTO);
//        assertNotNull(updatedUserPolicy);
//        assertEquals(954, updatedUserPolicy.getUserPolicyId());
//        assertEquals(2, updatedUserPolicy.getDurationInYears());
//    }
//
//	@Test
//	void testDeleteUserPolicyByPolicyId() {
//		String result=userPoliciesService.deleteUserPolicyById(302);
//		assertEquals("Record deleted", result);
//        
//        //assertNull(service.getbyUserPolicyId(1));
//	}
//
//
//	@Test
//	void testGetAllUserPolicies() {
//		List<UserPolicies> list = userPoliciesService.getAllUserPolicies();
//		boolean flag = list.isEmpty();
//		assertFalse(flag);
//	}
//	@Test
//	void testGetByUserPolicyId() {
//	    UserPolicies foundUserPolicy = userPoliciesService.getbyUserPolicyId(252);
//	    assertNotNull(foundUserPolicy);
//	    assertEquals(252, foundUserPolicy.getUserPolicyId());
//	}
//	
//	
//	@Test
//	void testGetUserPoliciesByUserId() {
//		List<UserPolicies> userpolicies = userPoliciesService.getUserPoliciesByUserId(1);
//		assertNotNull(userpolicies);
//		for (UserPolicies policy : userpolicies) {
//			Users retrivedUser = policy.getUser();		
//			assertEquals(1,retrivedUser.getUserId() );
//		}
//	}
//}