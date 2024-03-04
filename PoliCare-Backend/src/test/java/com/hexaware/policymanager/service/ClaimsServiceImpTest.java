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

import com.hexaware.policymanager.dto.ClaimsDTO;
import com.hexaware.policymanager.entities.Claims;
import com.hexaware.policymanager.exception.ClaimNotFoundException;

@SpringBootTest
class ClaimsServiceImpTest {
	@Autowired
	IClaimsService claimsService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testRegisterClaims() {

		ClaimsDTO claimsDTO = new ClaimsDTO();
		claimsDTO.setClaimStatus("Rejected");
		claimsDTO.setClaimAmount(10000);
		claimsDTO.setClaimDate(LocalDate.of(2024, 02, 16));
		claimsDTO.setUserPolicyId(2);
		Claims createdClaims = claimsService.registerClaims(claimsDTO);
		assertNotNull(createdClaims);
		assertEquals("Rejected", createdClaims.getClaimStatus());
		assertEquals(2, createdClaims.getUserPolicy().getUserPolicyId());
	}

	@Test
	void testUpdateClaims() throws ClaimNotFoundException {
		Claims claims = claimsService.getClaimsById(2);
		ClaimsDTO claimsDTO = new ClaimsDTO();
		claimsDTO.setClaimId(claims.getClaimId());
		claimsDTO.setClaimStatus("Rejected");
		claimsDTO.setClaimAmount(10000);
		claimsDTO.setClaimDate(LocalDate.of(2024, 02, 16));
		claimsDTO.setUserPolicyId(2);
		Claims updatedClaims = claimsService.updateClaims(claimsDTO);
		assertEquals(10000, updatedClaims.getClaimAmount());
	}

	@Test
	void testDeleteClaimsById() throws ClaimNotFoundException {
		String result = claimsService.deleteClaimsById(202);
		assertEquals("Record deleted", result);
	}

	@Test
	void testGetClaimsById() throws ClaimNotFoundException {
		Claims claims = claimsService.getClaimsById(52);
		assertNotNull(claims);
		assertEquals(52, claims.getClaimId());
	}

	@Test
	void testGetAllClaims() {
		List<Claims> list = claimsService.getAllClaims();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

	@Test
	void testGetAllClaimsByClaimAmount() throws ClaimNotFoundException {
		List<Claims> claimsList = claimsService.getAllClaimsByClaimAmount(100);
		for (Claims claims : claimsList) {
			assertEquals(100, claims.getClaimAmount());
		}

	}

	@Test
	void testGetAllClaimsByClaimStatus() throws ClaimNotFoundException {
		List<Claims> claimsList = claimsService.getAllClaimsByClaimStatus("Approved");
		for (Claims claims : claimsList) {
			assertEquals("Approved", claims.getClaimStatus());
		}
	}

}