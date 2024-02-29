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
//import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
//import com.hexaware.policymanager.entities.PolicyPayments;
//@SpringBootTest
//class PolicyPaymentsServiceImpTest {
//	@Autowired
//	IPolicyPaymentsService paymentsService;
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@Test
//	void testMakePayment() {
//		PolicyPaymentsDTO policyPaymentsDTO = new PolicyPaymentsDTO();
//		policyPaymentsDTO.setPaymentDate(LocalDate.of(2024, 02, 16));
//		policyPaymentsDTO.setPaymentStatus("Pending");
//		policyPaymentsDTO.setPaymentMethod("Credit Card");
//		policyPaymentsDTO.setUserPolicyId(40000);
//		PolicyPayments createdPayments = paymentsService.makePayment(policyPaymentsDTO);
//		assertNotNull(createdPayments);
//		assertEquals("Pending", createdPayments.getPaymentStatus());
//		assertEquals(40000, createdPayments.getUserPolicies().getUserPolicyId());
//	}
//
//	@Test
//	void testUpdatePayment() {
//		PolicyPayments policyPayments = paymentsService.getByPaymentId(102);
//		PolicyPaymentsDTO policyPaymentsDTO = new PolicyPaymentsDTO();
//		policyPaymentsDTO.setPaymentId(policyPayments.getPaymentId());
//		policyPaymentsDTO.setPaymentDate(LocalDate.of(2024, 02, 16));
//		policyPaymentsDTO.setPaymentStatus("Completed");
//		policyPaymentsDTO.setPaymentMethod("Credit Card");
//		policyPaymentsDTO.setUserPolicyId(40000);
//		PolicyPayments updatedPayments = paymentsService.updatePayment(policyPaymentsDTO);
//		assertEquals("Completed", updatedPayments.getPaymentStatus());
//
//	}
//
//	@Test
//	void testDeletePayment() {
//		String result = paymentsService.deletePayment(102);
//		assertEquals("Payment deleted", result);
//	}
//
//	@Test
//	void testGetByPaymentId() {
//		PolicyPayments payments = paymentsService.getByPaymentId(52);
//		assertNotNull(payments);
//		assertEquals(52, payments.getPaymentId());
//	}
//
//	@Test
//	void testGetAllPayments() {
//		List<PolicyPayments> list = paymentsService.getAllPayments();
//		boolean flag = list.isEmpty();
//		assertFalse(flag);
//	}
//
//	@Test
//	void testGetPaymentsByPaymentStatus() {
//		List<PolicyPayments> paymentsList = paymentsService.getPaymentsByPaymentStatus("Pending");
//		for (PolicyPayments policyPayments : paymentsList) {
//			assertEquals("Pending", policyPayments.getPaymentStatus());
//		}
//	}
//
//}
