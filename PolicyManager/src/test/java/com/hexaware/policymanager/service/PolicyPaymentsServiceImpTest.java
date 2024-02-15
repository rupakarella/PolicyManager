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
import com.hexaware.policymanager.exception.PaymentNotFoundException;

@SpringBootTest
class PolicyPaymentsServiceImpTest {
	
	@Autowired
	IPolicyPaymentsService paymentsService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	void testMakePayment() {
		PolicyPaymentsDTO policyPaymentsDTO = new PolicyPaymentsDTO();
		policyPaymentsDTO.setPaymentDate(LocalDate.of(2024, 02, 19));
		policyPaymentsDTO.setPaymentStatus("Pending");
		policyPaymentsDTO.setPaymentMethod("Credit Card");
		policyPaymentsDTO.setUserPolicyId(40002);
		PolicyPayments createdPayments = paymentsService.makePayment(policyPaymentsDTO);
		assertNotNull(createdPayments);
		assertEquals("Pending", createdPayments.getPaymentStatus());
		assertEquals(40002, createdPayments.getUserPolicies().getUserPolicyId());
	}
	@Test
	void testUpdatePayment() throws PaymentNotFoundException {
		PolicyPayments policyPayments = paymentsService.getByPaymentId(1);
		PolicyPaymentsDTO policyPaymentsDTO = new PolicyPaymentsDTO();
		policyPaymentsDTO.setPaymentId(policyPayments.getPaymentId());
		policyPaymentsDTO.setPaymentDate(LocalDate.of(2024, 02, 16));
		policyPaymentsDTO.setPaymentStatus("Completed");
		policyPaymentsDTO.setPaymentMethod("Credit Card");
		policyPaymentsDTO.setUserPolicyId(1);
		PolicyPayments updatedPayments = paymentsService.updatePayment(policyPaymentsDTO);
		assertEquals("Completed", updatedPayments.getPaymentStatus());

	}

	@Test
	void testDeletePayment() throws PaymentNotFoundException {
		String result = paymentsService.deletePayment(9000001);
		assertEquals("Payment deleted", result);
	}

	@Test
	void testGetByPaymentId() throws PaymentNotFoundException {
		PolicyPayments payments = paymentsService.getByPaymentId(9000002);
		assertNotNull(payments);
		assertEquals(9000002, payments.getPaymentId());
	}

	@Test
	void testGetAllPayments() {
		List<PolicyPayments> list = paymentsService.getAllPayments();
		boolean flag = list.isEmpty();
		assertFalse(flag);
	}

	@Test
	void testGetPaymentsByPaymentStatus() throws PaymentNotFoundException {
		List<PolicyPayments> paymentsList = paymentsService.getPaymentsByPaymentStatus("Completed");
		for (PolicyPayments policyPayments : paymentsList) {
			assertEquals("Completed", policyPayments.getPaymentStatus());
		}
	}

}