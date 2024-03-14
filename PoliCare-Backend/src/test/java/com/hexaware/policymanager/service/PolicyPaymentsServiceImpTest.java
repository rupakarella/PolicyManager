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

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;
@SpringBootTest
class PolicyPaymentsServiceImpTest {
	@Autowired
	IPolicyPaymentsService paymentsService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testMakePayment() {
		PolicyPaymentsDTO policyPaymentsDTO = new PolicyPaymentsDTO();
		policyPaymentsDTO.setPaymentDate(LocalDate.of(2024, 02, 16));
		policyPaymentsDTO.setPaymentMethod("Card");
		policyPaymentsDTO.setUserPolicyId(40051);
		policyPaymentsDTO.setUserId(10014);
		policyPaymentsDTO.setPaymentStatus("Completed");
		PolicyPayments createdPayments = paymentsService.makePayment(policyPaymentsDTO);
		assertNotNull(createdPayments);
		assertEquals(40051, createdPayments.getUserPolicies().getUserPolicyId());
	}

	@Test
	void testUpdatePayment() {
		PolicyPayments policyPayments = paymentsService.getByPaymentId(302);
		PolicyPaymentsDTO policyPaymentsDTO = new PolicyPaymentsDTO();
		policyPaymentsDTO.setPaymentId(policyPayments.getPaymentId());
		policyPaymentsDTO.setPaymentDate(LocalDate.of(2024, 02, 16));
		policyPaymentsDTO.setPaymentStatus("Completed");
		policyPaymentsDTO.setPaymentMethod("Card");
		policyPaymentsDTO.setUserPolicyId(40051);
		policyPaymentsDTO.setUserId(10014);
		PolicyPayments updatedPayments = paymentsService.updatePayment(policyPaymentsDTO);
		assertEquals("Completed", updatedPayments.getPaymentStatus());

	}

	@Test
	void testDeletePayment() {
		String result = paymentsService.deletePayment(202);
		assertEquals("Payment deleted", result);
	}

	@Test
	void testGetByPaymentId() {
		PolicyPayments payments = paymentsService.getByPaymentId(302);
		assertNotNull(payments);
		assertEquals(302, payments.getPaymentId());
	}

	@Test
	void testGetAllPayments() {
		List<PolicyPayments> list = paymentsService.getAllPayments();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

	@Test
	void testGetPaymentsByPaymentStatus() {
		List<PolicyPayments> paymentsList = paymentsService.getPaymentsByPaymentStatus("Completed");
		for (PolicyPayments policyPayments : paymentsList) {
			assertEquals("Completed", policyPayments.getPaymentStatus());
		}
	}

}
