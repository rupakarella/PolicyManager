package com.hexaware.policymanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.repository.PolicyPaymentsRepository;

public class PolicyPaymentsServiceImp implements IPolicyPaymentsService {

	@Autowired
	PolicyPaymentsRepository paymentrepo;

	@Override
	public PolicyPayments createPolicyPayment(PolicyPaymentsDTO policyPaymentDTO) {
		PolicyPayments policyPayment = new PolicyPayments();
		policyPayment.setUserPolicy(policyPaymentDTO.getUserPolicy());
		policyPayment.setTransactionId(policyPaymentDTO.getTransactionId());
		policyPayment.setPaymentDate(policyPaymentDTO.getPaymentDate());
		policyPayment.setBank(policyPaymentDTO.getBank());
		policyPayment.setAmount(policyPaymentDTO.getAmount());
		policyPayment.setFine(policyPaymentDTO.getFine());
		policyPayment.setPaymentStatus(policyPaymentDTO.getPaymentStatus());

		PolicyPayments createdPolicyPayment = paymentrepo.save(policyPayment);
		return createdPolicyPayment;
	}

	@Override
	public PolicyPayments updatePolicyPayment(PolicyPaymentsDTO policyPaymentDTO) {
		PolicyPayments policyPayment = new PolicyPayments();
		policyPayment.setUserPolicy(policyPaymentDTO.getUserPolicy());
		policyPayment.setTransactionId(policyPaymentDTO.getTransactionId());
		policyPayment.setPaymentDate(policyPaymentDTO.getPaymentDate());
		policyPayment.setBank(policyPaymentDTO.getBank());
		policyPayment.setAmount(policyPaymentDTO.getAmount());
		policyPayment.setFine(policyPaymentDTO.getFine());
		policyPayment.setPaymentStatus(policyPaymentDTO.getPaymentStatus());

		PolicyPayments updatePolicyPayment = paymentrepo.save(policyPayment);
		return updatePolicyPayment;
	}

	@Override
	public void deletePolicyPaymentByTxnId(long txnId) {
		paymentrepo.deleteByTransactionId(txnId);

	}

	@Override
	public PolicyPayments getPolicyPaymentBytransactionId(long txnId) {

		return paymentrepo.findById(txnId).orElse(null);
	}

	@Override
	public List<PolicyPayments> getAllPolicyPayments() {

		return paymentrepo.findAll();
	}

}
