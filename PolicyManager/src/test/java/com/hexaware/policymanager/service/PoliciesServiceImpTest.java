package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.repository.PoliciesRepository;

@SpringBootTest
class PoliciesServiceImpTest {

	@Autowired
	IPoliciesService policyservice;

	@Autowired
	PoliciesRepository policiesRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testCreatepolicy() {
		PoliciesDTO policyDTO = new PoliciesDTO();
		policyDTO.setPolicyId(1);
		policyDTO.setPolicyName("Life Insurance");
		policyDTO.setPolicyDescription("Insurance for life");
		policyDTO.setCompany("LIC");
		policyDTO.setPolicyType("Life");
		policyDTO.setMaturityAmount(10000.0);
		policyDTO.setInitialDeposit(5000.0);
		policyDTO.setTermPeriod("Monthly");
		policyDTO.setTermAmount(20000.0);
		policyDTO.setInterest(5.0);
		policyservice.createPolicy(policyDTO);

		assertNotNull(policyDTO);
		assertEquals(1, policyDTO.getPolicyId());
		assertEquals("Life Insurance", policyDTO.getPolicyName());

	}

//	@Test
//	void testUpdatePolicy() {
//		Policies policy = policyservice.getbyPolicyId(200);
//		policy.setPolicyName("Bheema");
//		policy.setPolicyDescription("One policy for security");
//		policy.setPolicyType("Health");
//		policy.setCompany("Bajaj");
//		policy.setMaturityAmount(5000.0);
//		policy.setInitialDeposit(1000.0);
//		policy.setInterest(2.0);
//
//		policyservice.updatePolicy(policyDTO);
//
//		Policies updatedPolicy = policyservice.getbyPolicyId(200);
//		assertEquals("Bheema", updatedPolicy.getPolicyName());
//		assertNotNull(updatedPolicy);
//
//	}

	@Test
	void testdeletePolicy() {
		Policies result = policyservice.deleteByPolicyId(203);
		Policies policyDTO = policyservice.getbyPolicyId(203);
		assertNull(policyDTO, "Deleted policy should be null");
	}

	@Test
	void testGetPolicyByPolicyType() {
		List<Policies> policies = policyservice.getPolicyByPolicyType("Medical");
		assertNotNull(policies);
		for (Policies policy : policies) {
			String retrievedPolicyType = policy.getPolicyType();
			assertEquals("Medical", retrievedPolicyType);
		}
	}

	@Test
	void testGetPolicyByCompany() {
		List<Policies> policies = policyservice.getPolicyByCompany("LIC");
		assertNotNull(policies);
		for (Policies policy : policies) {
			String company = policy.getCompany();
			assertEquals("LIC", company);
		}
	}

	@Test
	void testGetBytermAmountLessThan() {
		List<Policies> policies = policyservice.getBytermAmountLessThan(3500.0);
		assertNotNull(policies);
		for (Policies policy : policies) {
			double termAmount = policy.getTermAmount();
			assertEquals(true, termAmount < 3500.0);
		}
	}

	@Test
	void testGetBytermAmountGreaterThan() {
		List<Policies> policies = policyservice.getBytermAmountGreaterThan(3500.0);
		assertNotNull(policies);
		for (Policies policy : policies) {
			double termAmount = policy.getTermAmount();
			assertEquals(true, termAmount > 3500);
		}
	}

	@Test
	void testGetbyPolicyId() {
		Policies policyDTO = policyservice.getbyPolicyId(1);
		assertNotNull(policyDTO);
		assertEquals(1, policyDTO.getPolicyId());

	}
	
	@Test
	void testGetAllPolicies() {
		List<Policies> policyDTO = policyservice.getAllPolicies();
		boolean flag = policyDTO.isEmpty();
		assertFalse(flag);
		
	}

}