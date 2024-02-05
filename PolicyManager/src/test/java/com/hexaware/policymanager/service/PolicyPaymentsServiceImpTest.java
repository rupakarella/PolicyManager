package com.hexaware.policymanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;

@SpringBootTest
class PolicyPaymentsServiceImpTest {

	@Autowired
	IPolicyPaymentsService service;

	@Test
	void testCreatePolicyPayment() {
		PolicyPaymentsDTO policypaymentDTO = new PolicyPaymentsDTO();
		policypaymentDTO.setPaymentId(55);
		policypaymentDTO.setTransactionId(8000);
		policypaymentDTO.setPaymentDate(LocalDate.now());
		policypaymentDTO.setBank("SBI");
		policypaymentDTO.setAmount(10000.0);
		policypaymentDTO.setFine(500.0);
		policypaymentDTO.setPaymentStatus("Pending");

		service.createPolicyPayment(policypaymentDTO);
		assertNotNull(policypaymentDTO);

	}

	@Test
	void testUpdatePolicyPayment() {
		PolicyPaymentsDTO policypaymentDTO = service.getPolicyPaymentBytransactionId(6000);
		policypaymentDTO.setBank("HDFC");
		policypaymentDTO.setPaymentDate(LocalDate.now());
		policypaymentDTO.setAmount(25000.0);
		policypaymentDTO.setFine(5000.0);
		policypaymentDTO.setPaymentStatus("Pending");

		service.updatePolicyPayment(policypaymentDTO);

		PolicyPaymentsDTO updatedPayment = service.getPolicyPaymentBytransactionId(6000);
		assertEquals("HDFC",updatedPayment.getBank());
	}

	@Test
	void testDeletePolicyPaymentByTransactionId() {
		service.deletePolicyPaymentByTransactionId(5000);
		PolicyPaymentsDTO deletedpaymentDTO = service.getPolicyPaymentBytransactionId(5000);
		assertNull(deletedpaymentDTO, "Deleted policy should be null");

	}

	@Test
	void testGetPolicyPaymentBytransactionId() {
		PolicyPaymentsDTO policypaymentDTO = service.getPolicyPaymentBytransactionId(6000);
		assertNotNull(policypaymentDTO);
		assertEquals(6000, policypaymentDTO.getTransactionId());

	}

	@Test
	void testGetAllPolicyPayments() {
		List<PolicyPayments> policyDTO = service.getAllPolicyPayments();
		boolean flag = policyDTO.isEmpty();
		assertFalse(flag);
	}

}
