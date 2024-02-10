package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.PoliciesDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.exception.PolicyNotFoundException;
import com.hexaware.policymanager.exception.PolicyRegisteredByUserException;
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
		policyDTO.setPolicyName("Life Insurance");
		policyDTO.setPolicyDescription("Insurance for life");
		policyDTO.setCompany("LIC");
		policyDTO.setPolicyType("Life");
		policyDTO.setInitialDeposit(5000.0);
		policyDTO.setTermPeriod("Monthly");
		policyDTO.setTermAmount(20000.0);
		policyDTO.setInterest(5.0);
		policyservice.createPolicy(policyDTO);

		assertNotNull(policyDTO);
		assertEquals("Life Insurance", policyDTO.getPolicyName());

	}

	@Test
	void testUpdatePolicy() {
		PoliciesDTO policyDTO = policyservice.getbyPolicyId(1);
		policyDTO.setPolicyName("Bheema");
		policyDTO.setPolicyDescription("One policy for security");
		policyDTO.setPolicyType("Health");
		policyDTO.setCompany("Bajaj");
		policyDTO.setInitialDeposit(1000.0);
		policyDTO.setTermPeriod("Monthly");
		policyDTO.setTermAmount(20000.0);
		policyDTO.setInterest(2.0);

		policyservice.updatePolicy(policyDTO);

		PoliciesDTO updatedPolicy = policyservice.getbyPolicyId(1);
		assertEquals("Bheema", updatedPolicy.getPolicyName());
		assertNotNull(updatedPolicy);

	}

	@Test
	void testdeletePolicy() throws PolicyNotFoundException, PolicyRegisteredByUserException {
		String result = policyservice.deleteByPolicyId(102);
		assertEquals("Policy deleted succesfully",result);
	}

	@Test
	void testGetPolicyByPolicyType() {
		List<Policies> policies = policyservice.getPolicyByPolicyType("Health");
		assertNotNull(policies);
		for (Policies policy : policies) {
			String retrievedPolicyType = policy.getPolicyType();
			assertEquals("Health", retrievedPolicyType);
		}
	}

	@Test
	void testGetPolicyByCompany() {
		List<Policies> policies = policyservice.getPolicyByCompany("Bajaj");
		assertNotNull(policies);
		for (Policies policy : policies) {
			String company = policy.getCompany();
			assertEquals("Bajaj", company);
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
		List<Policies> policies = policyservice.getBytermAmountGreaterThan(1000.0);
		assertNotNull(policies);
		for (Policies policy : policies) {
			double termAmount = policy.getTermAmount();
			assertEquals(true, termAmount > 1000);
		}
	}
	
	@Test
	void testGetbyPolicyId() {
		PoliciesDTO policyDTO = policyservice.getbyPolicyId(1);
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