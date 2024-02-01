package com.hexaware.policymanager.service;

import java.util.List;

import com.hexaware.policymanager.dto.PolicyPaymentsDTO;
import com.hexaware.policymanager.entities.PolicyPayments;

public interface IPolicyPaymentsService {
	public PolicyPayments createPolicyPayment(PolicyPaymentsDTO policyPaymentDTO);
	public PolicyPayments updatePolicyPayment(PolicyPaymentsDTO policyPaymentDTO);
	public void deletePolicyPaymentByTxnId(long txnId);
   
    
	public List<PolicyPayments> getAllPolicyPayments();
	PolicyPayments getPolicyPaymentBytransactionId(long txnId);

}
