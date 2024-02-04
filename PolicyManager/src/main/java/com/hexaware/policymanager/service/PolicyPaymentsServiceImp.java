package com.hexaware.policymanager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.Policies;
import com.hexaware.policymanager.entities.PolicyPayments;
import com.hexaware.policymanager.repository.PolicyPaymentsRepository;

@Service
public class PolicyPaymentsServiceImp implements IPolicyPaymentsService {

	@Autowired
	PolicyPaymentsRepository paymentrepo;
	
	Logger logger=LoggerFactory.getLogger(PolicyPaymentsServiceImp.class);

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
		
		logger.info("Created Policy Payment succcesfully: {}",createdPolicyPayment);
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
	public PolicyPayments deletePolicyPaymentByTransactionnId(long transactionId) {

		PolicyPayments deletedPolicypayment = paymentrepo.findById(transactionId).orElse(null);
		paymentrepo.deleteById(transactionId);
		return deletedPolicypayment;


	}

	@Override
	public PolicyPayments getPolicyPaymentBytransactionId(long transactionId) {

		return paymentrepo.findById(transactionId).orElse(null);
	}

	@Override
	public List<PolicyPayments> getAllPolicyPayments() {

		List<PolicyPayments> policypayments=paymentrepo.findAll();
		logger.info("Retrived all Policy Payments succesfully: {}",policypayments);
		return policypayments;
	}

}
