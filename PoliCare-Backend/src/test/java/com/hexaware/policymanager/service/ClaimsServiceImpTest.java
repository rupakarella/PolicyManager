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
		claimsDTO.setClaimStatus("Pending");
		claimsDTO.setClaimAmount(10000);
		claimsDTO.setClaimDate(LocalDate.of(2024, 02, 16));
		claimsDTO.setUserPolicyId(40002);
		claimsDTO.setUserId(10002);
		Claims createdClaims = claimsService.registerClaims(claimsDTO);
		assertNotNull(createdClaims);
		assertEquals("Pending", createdClaims.getClaimStatus());
		assertEquals(40002, createdClaims.getUserPolicy().getUserPolicyId());
	}

	@Test
	void testUpdateClaims() throws ClaimNotFoundException {
		Claims claims = claimsService.getClaimsById(130005);
		ClaimsDTO claimsDTO = new ClaimsDTO();
		claimsDTO.setClaimId(claims.getClaimId());
		claimsDTO.setClaimStatus("Rejected");
		claimsDTO.setClaimAmount(10000);
		claimsDTO.setClaimDate(LocalDate.of(2024, 02, 16));
		claimsDTO.setUserPolicyId(40002);
		claimsDTO.setUserId(10002);
		Claims updatedClaims = claimsService.updateClaims(claimsDTO);
		assertEquals(10000, updatedClaims.getClaimAmount());
		assertEquals("Rejected",updatedClaims.getClaimStatus());
	}

	@Test
	void testDeleteClaimsById() throws ClaimNotFoundException {
		String result = claimsService.deleteClaimsById(130005);
		assertEquals("Record deleted", result);
	}

	@Test
	void testGetClaimsById() throws ClaimNotFoundException {
		Claims claims = claimsService.getClaimsById(130005);
		assertNotNull(claims);
		assertEquals(130005, claims.getClaimId());
	}

	@Test
	void testGetAllClaims() {
		List<Claims> list = claimsService.getAllClaims();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

	@Test
	void testGetAllClaimsByClaimAmount() throws ClaimNotFoundException {
		List<Claims> claimsList = claimsService.getAllClaimsByClaimAmount(500);
		for (Claims claims : claimsList) {
			assertEquals(500, claims.getClaimAmount());
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
